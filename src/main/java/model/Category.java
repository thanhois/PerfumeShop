/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.ProductDAO;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @Column(name = "CategoryID")
    private int id;

    @Column(name = "CategoryName")
    private String name;

    @Column(name = "Description")
    private String describe;

    @Column(name = "status")
    private Integer status; // phải thêm để dùng điều kiện WHERE c.status = 1

    public Category() {
    }

    public Category(int id, String name, String describe) {
        this.id = id;
        this.name = name;
        this.describe = describe;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotalProduct() {
        EntityManager em = util.JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            ProductDAO p = new ProductDAO(em);
            List<Product> list = p.getProductsByCategoryid(this.id);
            return list.size();
        } finally {
            em.close();
        }
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", status=" + status +
                '}';
    }
}