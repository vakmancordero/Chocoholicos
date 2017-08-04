package com.devlabs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="provider", catalog="chocoholicos")
public class Provider implements Serializable {
    
    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String cp;
    private String user;
    private String password;
    private List<Record> records = new ArrayList<>();
    
    public Provider() {
        
    }
    
    public Provider(String name, String address, 
            String city, String state, String cp) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.cp = cp;
    }
    public Provider(String name, String address, 
            String city, String state, String cp, List<Record> records) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.cp = cp;
        this.records = records;
    }
    
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="name", nullable=false)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="address", nullable=false)
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name="city", nullable=false)
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    @Column(name="state", nullable=false)
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    @Column(name="cp", nullable=false)
    public String getCp() {
        return this.cp;
    }
    
    public void setCp(String cp) {
        this.cp = cp;
    }

    @Column(name="user", nullable=false)
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    @Column(name="password", nullable=false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="provider")
    public List<Record> getRecords() {
        return this.records;
    }
    
    public void setRecords(List<Record> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
