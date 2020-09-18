package com.itzixue.service.impl;

import com.itzixue.dataobject.ProductCategory;
import com.itzixue.repository.ProductCategoryRepository;
import com.itzixue.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Mr.Dong
 * @create 2019-05-26 12:19
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Override
    @Transactional
    public ProductCategory save(ProductCategory productCategory) {
        return categoryRepository.save(productCategory);
    }

    @Override
    public List<ProductCategory> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public ProductCategory findOne(Integer categoryId) {
        Optional<ProductCategory> productCategory = categoryRepository.findById(categoryId);
        return productCategory.orElse(null);
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return categoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
