/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Supplier;
import java.util.List;

public class SupplierDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PerfumeShopPU");

    private EntityManager em;

    public SupplierDAO(EntityManager em) {
        this.em = em;
    }

    public List<Supplier> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Supplier> query = em.createQuery("SELECT s FROM Supplier s", Supplier.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Supplier> getSuppliersBySearch(String txt) {
    TypedQuery<Supplier> query = em.createQuery(
        "SELECT s FROM Suppliers s WHERE LOWER(s.companyName) LIKE :txt", Supplier.class);
    query.setParameter("txt", "%" + txt.toLowerCase() + "%");
    return query.getResultList();
}

    public Supplier getSupplierById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Supplier.class, id);
        } finally {
            em.close();
        }
    }

    public List<Supplier> searchByCompanyName(String keyword) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Supplier> query = em.createQuery("SELECT s FROM Suppliers s WHERE s.companyName LIKE :keyword", Supplier.class);
            query.setParameter("keyword", "%" + keyword + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void insertSupplier(Supplier s) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("insertSupplier: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void updateSupplier(Supplier s) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(s);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("updateSupplier: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void deleteSupplier(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Supplier s = em.find(Supplier.class, id);
            if (s != null) {
                em.remove(s);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("deleteSupplier: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public int countSuppliers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(s) FROM Supplier s", Long.class);
            return query.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

    public int countAllSupplier() {
        Long count = em.createQuery("SELECT COUNT(s) FROM Supplier s", Long.class).getSingleResult();
        return count.intValue();
    }
}
