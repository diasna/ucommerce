/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fursel.tenant.domain;

import com.fursel.persistence.Category;
import com.fursel.persistence.Product;
import com.fursel.util.enums.ProductCondition;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
    
    private BigDecimal retailPrice;
    
    private BigDecimal wholeshalePrice;
    
    private int quantity = 0;
    
    private ProductCondition condition;
    
    private List<MultipartFile> images = new ArrayList<MultipartFile>();
    
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

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getWholeshalePrice() {
        return wholeshalePrice;
    }

    public void setWholeshalePrice(BigDecimal wholeshalePrice) {
        this.wholeshalePrice = wholeshalePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductCondition getCondition() {
        return condition;
    }

    public void setCondition(ProductCondition condition) {
        this.condition = condition;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }
    
    public Product toEntity() {
        Product product = new Product();
        product.setName(this.getName());
        product.setDescription(this.getDescription());
        product.setCategory(new Category(this.getCategory()));
        product.setRetailPrice(this.getRetailPrice());
        product.setWholesalePrice(this.getWholeshalePrice());
        product.setQuantity(this.getQuantity());
        product.setCondition(this.getCondition().value());
        return product;
    }
}
