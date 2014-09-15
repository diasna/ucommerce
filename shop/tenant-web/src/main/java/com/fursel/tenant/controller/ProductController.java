/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fursel.tenant.controller;

import com.fursel.persistence.Product;
import com.fursel.persistence.service.CategoryService;
import com.fursel.persistence.service.ProductService;
import com.fursel.tenant.domain.PageWrapper;
import com.fursel.tenant.domain.ProductForm;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Dias Nurul Arifin <dias@nsiapay.net>
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String landing(Model uiModel, Pageable pageable, @ModelAttribute("product") ProductForm product) {
        PageWrapper<Product> page = new PageWrapper<Product>(productService.getProducts(pageable, product.getQuery()), "/products");
        uiModel.addAttribute("page", page);
        uiModel.addAttribute("categories", categoryService.getAllByTenant());
        return "/product";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute("product") ProductForm product, BindingResult result, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            return "redirect:/products";
        }
        productService.addProduct(product.toEntity());
        redirectAttrs.addFlashAttribute("message", "Product Created !");
        return "redirect:/products";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute("product") ProductForm product, BindingResult result, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            return "redirect:/products";
        }
        productService.deleteProduct(product.getId());
        redirectAttrs.addFlashAttribute("message", "Product Deleted !");
        return "redirect:/products";
    }
}
