package com.itzixue.service;

import com.itzixue.dataobject.ProductCategory;

import java.util.List;

/**
 * @author Mr.Dong
 * @create 2019-05-26 12:15
 */
public interface CategoryService {

    ProductCategory save(ProductCategory productCategory);

    List<ProductCategory> findAll();

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
