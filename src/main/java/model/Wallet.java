/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Wallets")
public class Wallet {

    @Id
    @Column(name = "UserName", nullable = false, length = 50)
    private String userName;
    
    @MapsId
    @OneToOne
    @JoinColumn(name = "UserName", referencedColumnName = "UserName")
    private User user;


    @Column(name = "Balance")
    private double balance;

    public Wallet() {
    }

    public Wallet(String userName, double balance) {
        this.userName = userName;
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    @Override
    public String toString() {
        return "Wallet{" +
                "userName='" + userName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
