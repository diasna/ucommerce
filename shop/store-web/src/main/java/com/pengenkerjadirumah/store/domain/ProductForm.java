package com.pengenkerjadirumah.store.domain;

import java.math.BigDecimal;

public class ProductForm {

    private String id;
    private String name;
    private BigDecimal cost;

    public ProductForm() {
    }

    public ProductForm(String id, String name, BigDecimal cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
