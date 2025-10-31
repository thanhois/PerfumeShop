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
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.Category;

public class CategoryDAO {
    private EntityManager em;

    public CategoryDAO(EntityManager em) {
        this.em = em;
    }

    public List<Category> getAll() {
        String jpql = "SELECT c FROM Category c WHERE c.status = 1";
        TypedQuery<Category> query = em.createQuery(jpql, Category.class);
        return query.getResultList();
    }

    public Category getCategoryById(int id) {
        try {
            return em.createQuery(
                    "SELECT c FROM Category c WHERE c.id = :id AND c.status = 1", Category.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null; // Có thể là không tìm thấy hoặc lỗi
        }
    }
}