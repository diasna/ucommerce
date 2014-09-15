/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fursel.tenant.domain;

import com.fursel.persistence.Category;
import com.fursel.persistence.Product;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Dias Nurul Arifin <dias@nsiapay.net>
 */
public class ProductForm {

    private Long id;
    @NotNull
    @NotEmpty
    private String name;
    private String description;
    @NotNull
    private Long category;
    private String query = "";

    public ProductForm() {
    }

    public ProductForm(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Product toEntity() {
        Product product = new Product();
        product.setName(this.getName());
        product.setDescription(this.getDescription());
        product.setCategory(new Category(this.getCategory()));
        return product;
    }
}
