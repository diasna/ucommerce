package com.pengenkerjadirumah.tenant.controller;

import java.util.List;

import com.pengenkerjadirumah.persistence.json.CategoryJson;
import com.pengenkerjadirumah.persistence.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/raw")
public class DataJsonController extends AbstractController{
	
	@Autowired
    private CategoryService categoryService;
	
	@RequestMapping(value ="/categories", method=RequestMethod.GET, produces = {"application/json"})   
    public @ResponseBody List<CategoryJson> getCategories(){
        return categoryService.getAllByTenant(getUser().getTenantId());
    }
}
