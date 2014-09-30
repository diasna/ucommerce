/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fursel.tenant.controller;

import com.fursel.persistence.Images;
import com.fursel.persistence.Product;
import com.fursel.persistence.security.TenantUserDetails;
import com.fursel.persistence.service.CategoryService;
import com.fursel.persistence.service.ProductService;
import com.fursel.tenant.domain.PageWrapper;
import com.fursel.tenant.domain.ProductForm;
import com.fursel.util.Util;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
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
    public String add(@Valid @ModelAttribute("product") ProductForm form, BindingResult result, RedirectAttributes redirectAttrs) {
        
        if (result.hasErrors()) {
            return "redirect:/products";
        }
        
        Product product = productService.addProduct(form.toEntity());
        if (product != null) {
            TenantUserDetails userDetails = (TenantUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<MultipartFile> files = form.getImages();
            int filename = 1;
            if(null != files && files.size() > 0) {
                for (MultipartFile multipartFile : files) {
                    if(!multipartFile.isEmpty()){
                        try {
                            String imagesRepositoryLocation = "/Users/diasnurularifin/images/";
                            LOG.info("Uploaded File: "+multipartFile.getOriginalFilename());
                            File dir = new File(imagesRepositoryLocation+userDetails.getTenantId()+"/"+product.getId());
                            dir.mkdirs();
                            File file = new File(imagesRepositoryLocation+userDetails.getTenantId()+"/"+product.getId()+"/"+filename+".png");
                            Util.putFileToSystem(file, multipartFile.getInputStream());
                            filename++;
                            LOG.info("Persisting to database ..");
                            Images i = new Images();
                            i.setProduct(product);
                            i.setSystemPath(filename+".png");
                            productService.addImages(i);
                        } catch (IOException ex) {
                            java.util.logging.Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            redirectAttrs.addFlashAttribute("status", 0);
        } else {
            redirectAttrs.addFlashAttribute("status", 1);
            LOG.error("Product is null :"+ form.getName());
        }
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
