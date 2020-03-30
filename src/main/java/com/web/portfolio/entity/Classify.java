package com.web.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Classify implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String name;
    
    @Column
    private Boolean transaction;
    
    @OneToMany(cascade=CascadeType.PERSIST, mappedBy="classify")
    @JsonIgnoreProperties("classify")
    private Set<TStock> tStocks;

    public Classify() {
    }

    public Classify(String name, Boolean transaction) {
        this.name = name;
        this.transaction = transaction;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TStock> gettStocks() {
        return tStocks;
    }

    public void settStocks(Set<TStock> tStocks) {
        this.tStocks = tStocks;
    }

    public Boolean getTransaction() {
        return transaction;
    }

    public void setTransaction(Boolean transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "Classify{" + "id=" + id + ", name=" + name + ", transaction=" + transaction + '}';
    }
    
    
}
