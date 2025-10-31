/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author Lenovo
 */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "Supplier") // tên bảng trong SQL
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // nếu id tự tăng
    private int id;

    private String companyName;
    private String contactName;
    private String country;
    private String phone;
    private String homepage;

    public Supplier() {
    }

    public Supplier(int id, String companyName, String contactName, String country, String phone, String homepage) {
        this.id = id;
        this.companyName = companyName;
        this.contactName = contactName;
        this.country = country;
        this.phone = phone;
        this.homepage = homepage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public void setConTactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", homepage='" + homepage + '\'' +
                '}';
    }
}