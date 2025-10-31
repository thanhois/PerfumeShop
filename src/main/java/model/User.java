/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import java.util.List;
/**
 *
 * @author Lenovo
 */

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "UserName", nullable = false, length = 50)
    private String userName;

    @Column(name = "FullName", nullable = false, length = 100)
    private String fullName;

    @Column(name = "Password", nullable = false, length = 100)
    private String password;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Order> orders;
    
    @Column(name = "Address")
    private String address;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Image")
    private String image;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "RoleID")
    private int roleID;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "BirthDay")
    private String birthdate;

    // ===== Constructor =====
    public User() {
    }

    public User(String userName, String fullName, String password, String address, String phone,
                String email, String image, String birthdate, int roleID) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.birthdate = birthdate;
        this.roleID = roleID;
        this.status = 1; // mặc định là active
    }

    // ===== Getter/Setter =====
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}