package com.devlabs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="member", catalog="chocoholicos")
public class Member implements Serializable {

    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String cp;
    private List<Record> records = new ArrayList<>();
    
    public Member() {
        
    }
    
    public Member(String name, String address, String city, String state, String cp) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.cp = cp;
    }
    public Member(String name, String address, String city, String state, String cp, List<Record> records) {
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
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="member")
    public List<Record> getRecords() {
        return this.records;
    }
    
    public void setRecords(List<Record> records) {
        this.records = records;
    }

}
