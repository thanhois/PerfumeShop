/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private int orderId;
    
//    @Column(name = "UserName", insertable = false, updatable = false, nullable = false)
//    private String userName;

    @ManyToOne
    @JoinColumn(name = "UserName", referencedColumnName = "UserName", nullable = false)
    private User user;

    @Column(name = "Total")
    private Double total;

    @Temporal(TemporalType.DATE)
    @Column(name = "Date")
    private Date date;

    @Column(name = "Status")
    private boolean status;

    public Order() {
    }

    public Order(int orderId, Date date, String userName, double total, boolean status) {
        this.orderId = orderId;
//        this.userName = userName;
        this.user = user;
        this.total = total;
        this.date = date;
        this.status = status;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", user=" + (user != null ? user.getUserName() : "null") +
                ", total=" + total +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}