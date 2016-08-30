package com.pengenkerjadirumah.tenant.controller;

import com.pengenkerjadirumah.persistence.TenantUser;
import com.pengenkerjadirumah.tenant.bean.UserComponent;
import com.pengenkerjadirumah.tenant.security.TenantUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dias Nurul Arifin on 2/21/2016.
 */
@Controller
public abstract class AbstractController {

    @Autowired
    private UserComponent userComponent;

    protected Map<String, Object> getDefaultModelOutput(){
        Map<String, Object> map = new HashMap<String, Object>();
        return map;
    }

    protected ModelAndView getDefaultModelAndView(String view){
        return new ModelAndView(view, getDefaultModelOutput());
    }

    public TenantUserDetails getUser() {
        return userComponent.getUser();
    }

}
