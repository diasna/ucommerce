/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengenkerjadirumah.persistence;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Dias Nurul Arifin <dias@nsiapay.net>
 */
@Entity
@Table(name = "sp_order")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column
    private String reference;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @Column(name = "last_state")
    private char lastState;
    
    @OneToOne(mappedBy = "order")
    private Cart cart;
    
    @Column
    private Date date;
    
    @Column(name = "payment_method")
    private char paymentMethod;
    
    @Column(name = "total_product")
    private BigDecimal totalProduct;
    
    @Column(name = "total_shipping")
    private BigDecimal totalShipping;
    
    @Column(name = "carier_reference")
    private String carierReference;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Set<OrderHistory> history;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public char getLastState() {
        return lastState;
    }

    public void setLastState(char lastState) {
        this.lastState = lastState;
    }

    public Set<OrderHistory> getHistory() {
        return history;
    }

    public void setHistory(Set<OrderHistory> history) {
        this.history = history;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public char getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(char paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(BigDecimal totalProduct) {
        this.totalProduct = totalProduct;
    }

    public BigDecimal getTotalShipping() {
        return totalShipping;
    }

    public void setTotalShipping(BigDecimal totalShipping) {
        this.totalShipping = totalShipping;
    }

    public String getCarierReference() {
        return carierReference;
    }

    public void setCarierReference(String carierReference) {
        this.carierReference = carierReference;
    }
    
}
