/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Lenovo
 */
import jakarta.persistence.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;
import util.JPAUtil;

public class ProductDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PerfumeShopPU");
    private final DecimalFormat df = new DecimalFormat("###.##");
    private EntityManager em;

    public ProductDAO(EntityManager em) {
        this.em = em;
    }

    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    public List<Product> getAll() {
        return em.createQuery("SELECT p FROM Product p WHERE p.status = true", Product.class).getResultList();
    }

    public Product getProductByID(int id) {
        try {
            return em.createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class)
                     .setParameter("id", id)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Product> getProductsByCategoryid(int cid) {
        String jpql = "SELECT p FROM Product p WHERE p.category.id = :cid AND p.status = true";
        return em.createQuery(jpql, Product.class)
                 .setParameter("cid", cid)
                 .getResultList();
    }

    public List<Product> getByCategoryId(int categoryId) {
        String jpql = "SELECT p FROM Product p WHERE p.status = true AND p.category.id = :categoryId ORDER BY p.id";
        return em.createQuery(jpql, Product.class)
                 .setParameter("categoryId", categoryId)
                 .getResultList();
    }

    public List<Product> getTopBestSellers(int limit) {
        String jpql = "SELECT p FROM Product p WHERE p.status = true ORDER BY p.quantity DESC";
        return em.createQuery(jpql, Product.class).setMaxResults(limit).getResultList();
    }

    public Product getHotDeal() {
        String jpql = "SELECT p FROM Product p WHERE p.status = true ORDER BY (p.price - p.price * p.discount) ASC";
        List<Product> result = em.createQuery(jpql, Product.class).setMaxResults(1).getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public List<Product> getFeaturedProducts() {
        String jpql = "SELECT p FROM Product p WHERE p.status = true";
        return em.createQuery(jpql, Product.class).setMaxResults(3).getResultList();
    }

    public List<Product> getGiftSets() {
        String jpql = "SELECT p FROM Product p WHERE LOWER(p.name) LIKE '%gift%' AND p.status = true";
        return em.createQuery(jpql, Product.class).getResultList();
    }

    public List<Product> getProductsBrandByInYear(int year, Category category) {
        String jpql = "SELECT p FROM Product p WHERE FUNCTION('YEAR', p.date) = :year AND p.category IS NOT NULL AND p.category.id = :cid AND p.status = true";
        return em.createQuery(jpql, Product.class)
                 .setParameter("year", year)
                 .setParameter("cid", category.getId())
                 .getResultList();
    }

    public List<Product> getProductByYear(int year) {
        String jpql = "SELECT p FROM Product p WHERE FUNCTION('YEAR', p.date) = :year AND p.status = true";
        return em.createQuery(jpql, Product.class)
                 .setParameter("year", year)
                 .getResultList();
    }

    public List<Product> getProductByCategoryYear(int categoryId, int year) {
        String jpql = "SELECT p FROM Product p WHERE p.status = true AND p.category IS NOT NULL AND p.category.id = :cid AND FUNCTION('YEAR', p.date) = :year";
        return em.createQuery(jpql, Product.class)
                 .setParameter("cid", categoryId)
                 .setParameter("year", year)
                 .getResultList();
    }

    public void insertProduct(Product p) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(p);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    public List<Product> searchByName(String txt) {
        return em.createQuery(
                "SELECT p FROM Product p WHERE LOWER(p.name) LIKE :txt AND p.status = true", Product.class)
                .setParameter("txt", "%" + txt.toLowerCase() + "%")
                .getResultList();
    }

    public void updateProduct(Product p) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(p);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    public void deleteProduct(int productId) {
    EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
    EntityTransaction tx = em.getTransaction();

    try {
        tx.begin();
        Product p = em.find(Product.class, productId);
        if (p != null) {
            p.setStatus(false); // Soft delete
            em.merge(p);
        }
        tx.commit();
    } catch (Exception e) {
        if (tx.isActive()) {
            tx.rollback();
        }
        e.printStackTrace();
    } finally {
        em.close();
    }
}

    public List<Product> getListByPage(List<Product> list, int start, int end) {
        List<Product> arr = new ArrayList<>();
        for (int i = start; i < end && i < list.size(); i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public double getSalePrice(double price, double discount) {
        try {
            return Double.parseDouble(df.format(price * (1 - discount)));
        } catch (Exception e) {
            return price;
        }
    }

    public int countAllProduct() {
        Long count = em.createQuery("SELECT COUNT(p) FROM Product p", Long.class).getSingleResult();
        return count.intValue();
    }

    public int countAllTypeProduct() {
        Long count = em.createQuery("SELECT COUNT(DISTINCT p.category.id) FROM Product p", Long.class).getSingleResult();
        return count.intValue();
    }

    public int getSumQuantitySold() {
        Long sum = em.createQuery("SELECT SUM(p.quantity) FROM Product p", Long.class).getSingleResult();
        return (sum != null) ? sum.intValue() : 0;
    }

    public List<Product> getTop10SellerProduct() {
        return em.createQuery("SELECT p FROM Product p ORDER BY p.quantity DESC", Product.class)
                .setMaxResults(10)
                .getResultList();
    }
}