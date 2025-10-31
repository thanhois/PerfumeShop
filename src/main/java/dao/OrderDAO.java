/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import model.*;

public class OrderDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PerfumeShopPU");
    private EntityManager em;

    public OrderDAO(EntityManager em) {
        this.em = em;
    }

    public int getNumberOrders() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(o) FROM Order o", Long.class);
            return query.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

    public void addOrder(User user, Cart cart) {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();

        Order order = new Order();
        order.setDate(java.sql.Date.valueOf(LocalDate.now()));
        order.setUser(user);
        order.setTotal(cart.getTotalMoney());
        order.setStatus(false);

        System.out.println(">>> addOrder setUserName: " + user.getUserName()); // debug

        em.persist(order);

        for (Item item : cart.getListItems()) {
            Product product = em.find(Product.class, item.getProduct().getId());
            if (product != null) {
                product.setQuantity(product.getQuantity() - item.getQuantity());
                em.merge(product);
            }
        }

        em.getTransaction().commit();
    } catch (Exception e) {
        em.getTransaction().rollback();
        System.out.println("addOrder: " + e.getMessage());
    } finally {
        em.close();
    }
}

    public double totalMoneyMonth(int month, int year) {
        TypedQuery<Double> query = em.createQuery(
                "SELECT SUM(o.total) FROM Order o WHERE FUNCTION('MONTH', o.date) = :month AND FUNCTION('YEAR', o.date) = :year",
                Double.class
        );
        query.setParameter("month", month);
        query.setParameter("year", year);
        Double result = query.getSingleResult();
        return result != null ? result : 0;
    }

    public double totalMoneyWeek(int day, int from, int to, int year, int month) {
    EntityManager em = emf.createEntityManager();
    try {
        String sql;
        Query query;

        if (from > to) {
            sql = "SELECT SUM(o.Total) FROM Orders o " +
                  "WHERE ((DAY(o.Date) >= :from AND MONTH(o.Date) = :month) " +
                  "OR (DAY(o.Date) <= :to AND MONTH(o.Date) = :nextMonth)) " +
                  "AND YEAR(o.Date) = :year " +
                  "AND DATEPART(WEEKDAY, o.Date) = :day";
            query = em.createNativeQuery(sql);
            query.setParameter("nextMonth", month + 1);
        } else {
            sql = "SELECT SUM(o.Total) FROM Orders o " +
                  "WHERE DAY(o.Date) BETWEEN :from AND :to " +
                  "AND MONTH(o.Date) = :month " +
                  "AND YEAR(o.Date) = :year " +
                  "AND DATEPART(WEEKDAY, o.Date) = :day";
            query = em.createNativeQuery(sql);
        }

        query.setParameter("from", from);
        query.setParameter("to", to);
        query.setParameter("month", month);
        query.setParameter("year", year);
        query.setParameter("day", day);

        Object result = query.getSingleResult();
        return (result != null) ? ((Number) result).doubleValue() : 0;
    } finally {
        em.close();
    }
}

    public double sumAllMoneyOrder() {
        EntityManager em = emf.createEntityManager();
        double sum = 0.0;
        try {
            Query query = em.createQuery("SELECT SUM(o.total) FROM Order o");
            Double result = (Double) query.getSingleResult();
            if (result != null) {
                sum = result;
            }
        } catch (Exception e) {
            System.out.println("sumAllMoneyOrder: " + e.getMessage());
        } finally {
            em.close();
        }
        return sum;
    }


    public List<Order> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o ORDER BY o.status ASC", Order.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void updateStatus(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Order order = em.find(Order.class, id);
            if (order != null) {
                order.setStatus(true);
                em.merge(order);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("updateStatus: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
