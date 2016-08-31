package com.pengenkerjadirumah.persistence.service.impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.pengenkerjadirumah.persistence.json.CategoryJson;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pengenkerjadirumah.persistence.Category;
import com.pengenkerjadirumah.persistence.Tenant;
import com.pengenkerjadirumah.persistence.repository.CategoryRepository;
import com.pengenkerjadirumah.persistence.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryServiceImpl.class);
    
    @Autowired
    private CategoryRepository repository;
    
    @Autowired
    private EntityManagerFactory factory;
    
    @Override
    @Transactional(readOnly = false)
    public boolean addCategory(Category category, long tenantId) {
        category.setTenant(new Tenant(tenantId));
        return repository.save(category) != null ? true : false;
    }

    @Override
    public Page<Category> getCategories(Pageable pageable, String keywords, long tenantId) {
        return repository.findCategories(keywords, tenantId, pageable);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteCategory(long id) {
        repository.delete(id);
    }

    @Override
    public List<CategoryJson> getAllByTenant(long tenantId) {
        Session session = factory.createEntityManager().unwrap(Session.class);
        Criteria cr = session.createCriteria(Category.class)
        		.add(Restrictions.eq("tenant.id", tenantId))
        	    .setProjection(Projections.projectionList()
        	      .add(Projections.property("id"), "id")
        	      .add(Projections.property("parent.id"), "parent")
        	      .add(Projections.property("name"), "text"))
        	    .setResultTransformer(Transformers.aliasToBean(CategoryJson.class));
        return cr.list();
    }
}
