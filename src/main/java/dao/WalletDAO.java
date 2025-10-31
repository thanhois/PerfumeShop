/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Lenovo
 */

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Wallet;
import java.util.List;

public class WalletDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PerfumeShopPU");
    private EntityManager em;
    public WalletDAO(EntityManager em) {
        this.em = em;
    }
    public int getNumberWallets() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(w) FROM Wallet w", Long.class);
            return query.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

    public void addWallet(Wallet wallet) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(wallet);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("addWallet: " + e);
        } finally {
            em.close();
        }
    }

    public List<Wallet> getWalletBySearchName(String txtSearch) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Wallet> query = em.createQuery("SELECT w FROM Wallet w WHERE w.userName LIKE :txt", Wallet.class);
            query.setParameter("txt", "%" + txtSearch + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Wallet getWalletByUserName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Wallet> query = em.createQuery("SELECT w FROM Wallet w WHERE w.userName = :name", Wallet.class);
            query.setParameter("name", name);
            List<Wallet> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            em.close();
        }
    }

    public void inputMoney(String userName, double value) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Wallet wallet = em.createQuery("SELECT w FROM Wallet w WHERE w.userName = :name", Wallet.class)
                              .setParameter("name", userName)
                              .getSingleResult();
            if (wallet != null) {
                wallet.setBalance(wallet.getBalance() + value);
                em.merge(wallet);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("inputMoney: " + e);
        } finally {
            em.close();
        }
    }

    public void decuctionMoney(String userName, double value) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Wallet wallet = em.createQuery("SELECT w FROM Wallet w WHERE w.userName = :name", Wallet.class)
                              .setParameter("name", userName)
                              .getSingleResult();
            if (wallet != null) {
                wallet.setBalance(wallet.getBalance() - value);
                em.merge(wallet);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("decuctionMoney: " + e);
        } finally {
            em.close();
        }
    }

    public List<Wallet> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Wallet> query = em.createQuery("SELECT w FROM Wallet w ORDER BY w.balance DESC", Wallet.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}