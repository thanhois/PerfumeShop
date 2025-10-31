/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private int id;

    @Column(name = "ProductName")
    private String name;

    @Column(name = "describe")
    private String describe;

    @Column(name = "QuantityPerUnit")
    private String classifyStr;

    @Column(name = "image")
    private String imageRaw;

    @Column(name = "UnitsInStock")
    private int quantity;

    @Column(name = "StarRating")
    private int starRating;

    @Column(name = "UnitPrice")
    private double price;

    @Column(name = "Discount")
    private double discount;

    @Column(name = "releaseDate")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "SupplierID")
    private Supplier supplier;

    @Transient
    private double salePrice;

    @Transient
    private String[] image;

    public Product() {}

    public Product(String name, String[] image, String describe, String classifyStr, int id, int quantity, int starRating,
                   double price, double discount, double salePrice, Date date, Category category, Supplier supplier) {
        this.name = name;
        this.image = image;
        this.describe = describe;
        this.classifyStr = classifyStr;
        this.id = id;
        this.quantity = quantity;
        this.starRating = starRating;
        this.price = price;
        this.discount = discount;
        this.salePrice = salePrice;
        this.date = date;
        this.category = category;
        this.supplier = supplier;
        this.imageRaw = String.join(",", image);
    }

    @PostLoad
    public void initImages() {
        this.image = imageRaw != null ? imageRaw.split(",") : new String[0];
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescribe() { return describe; }
    public void setDescribe(String describe) { this.describe = describe; }

    public String getClassifyStr() { return classifyStr; } 
    public void setClassifyStr(String classifyStr) { this.classifyStr = classifyStr; }

    public String[] getImage() { return image; }
    public void setImage(String[] image) { this.image = image; }

    public void setImageRaw(String imageRaw) { this.imageRaw = imageRaw; } //  Để gọi setImage(String)
    public String getImageRaw() { return imageRaw; } // tuỳ bạn có cần không

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getStarRating() { return starRating; }
    public void setStarRating(int starRating) { this.starRating = starRating; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    public double getSalePrice() {
        double salePrice = price - Math.round(price * discount * 100) / 100.0;
        salePrice = Math.round(salePrice * 100.0) / 100.0;
        return discount > 0 ? salePrice : price;
    }

    public void setSalePrice(double salePrice) { this.salePrice = salePrice; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", image=" + Arrays.toString(image) +
                ", describe='" + describe + '\'' +
                ", classifyStr='" + classifyStr + '\'' +
                ", id=" + id +
                ", quantity=" + quantity +
                ", starRating=" + starRating +
                ", price=" + price +
                ", discount=" + discount +
                ", salePrice=" + salePrice +
                ", date=" + date +
                ", category=" + category +
                ", supplier=" + supplier +
                ", status=" + status +
                '}';
    }
}