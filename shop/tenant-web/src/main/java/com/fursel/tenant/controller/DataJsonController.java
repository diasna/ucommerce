package com.fursel.tenant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fursel.persistence.json.CategoryJson;
import com.fursel.persistence.service.CategoryService;

@Controller
@RequestMapping("/raw")
public class DataJsonController {
	
	@Autowired
    private CategoryService categoryService;
	
	@RequestMapping(value ="/categories", method=RequestMethod.GET, produces = {"application/json"})   
    public @ResponseBody List<CategoryJson> getCategories(){
        return categoryService.getAllByTenant();
    }
}
