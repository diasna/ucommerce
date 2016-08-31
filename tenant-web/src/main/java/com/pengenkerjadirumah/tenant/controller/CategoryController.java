package com.pengenkerjadirumah.tenant.controller;

import javax.validation.Valid;

import com.pengenkerjadirumah.persistence.Category;
import com.pengenkerjadirumah.persistence.service.CategoryService;
import com.pengenkerjadirumah.tenant.domain.CategoryForm;
import com.pengenkerjadirumah.tenant.domain.PageWrapper;
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


@Controller
@RequestMapping("/categories")
public class CategoryController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);
    
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String landing(Model uiModel, Pageable pageable, @ModelAttribute("category") CategoryForm category) {
        PageWrapper<Category> page = new PageWrapper<Category>(categoryService.getCategories(pageable, category.getQuery(), getUser().getTenantId()), "/categories");
        uiModel.addAttribute("page", page);
        return "/category";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute("category") CategoryForm category, BindingResult result, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            return "redirect:/categories";
        }
        categoryService.addCategory(category.toEntity(), getUser().getTenantId());
        redirectAttrs.addFlashAttribute("message", "Category Created !");
        return "redirect:/categories";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute("category") CategoryForm category, BindingResult result, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            return "redirect:/categories";
        }
        categoryService.deleteCategory(category.getId());
        redirectAttrs.addFlashAttribute("message", "Category Deleted !");
        return "redirect:/categories";
    }
    
    @ModelAttribute("category")
    private CategoryForm getCategory() {
        return new CategoryForm();
    }
}
