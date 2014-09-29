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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
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
    public String add(@Valid @ModelAttribute("product") ProductForm product, BindingResult result, RedirectAttributes redirectAttrs) {
//        if (result.hasErrors()) {
//            return "redirect:/products";
//        }
//        productService.addProduct(product.toEntity());
        
        List<MultipartFile> files = product.getImages();
         
        if(null != files && files.size() > 0) {
            for (MultipartFile multipartFile : files) {
                if(!multipartFile.isEmpty()){
                    try {
                        LOG.info("Uploaded File: "+multipartFile.getOriginalFilename());
                        File f = new File("/Users/diasnurularifin/Desktop/"+multipartFile.getOriginalFilename());
                        putFileToSystem(f, multipartFile.getInputStream());
                    } catch (IOException ex) {
                        java.util.logging.Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        redirectAttrs.addFlashAttribute("message", "Product Created !");
        return "redirect:/products";
    }

    private void putFileToSystem(File file, InputStream is) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = is.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            LOG.info("Done Upload :"+file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
