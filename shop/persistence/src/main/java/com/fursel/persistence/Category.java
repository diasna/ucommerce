package com.fursel.persistence;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sp_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Set<Category> child;

    public Category() {
    }

    public Category(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Category> getChild() {
        return child;
    }

    public void setChild(Set<Category> child) {
        this.child = child;
    }

}
