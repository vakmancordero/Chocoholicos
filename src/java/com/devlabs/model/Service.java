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
@Table(name="service", catalog="chocoholicos")
public class Service implements Serializable {

    private Long id;
    private String code;
    private String name;
    private double price;
    private List<Record> records = new ArrayList<>();
    
    public Service() {
        
    }
    
    public Service(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }
    public Service(String code, String name, double price, List<Record> records) {
        this.code = code;
        this.name = name;
        this.price = price;
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
    
    @Column(name="code", nullable=false)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(name="name", nullable=false)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="price", nullable=false, precision=22, scale=0)
    public double getPrice() {
        return this.price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="service")
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
