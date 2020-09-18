package com.itzixue.repository;

import com.itzixue.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @author Mr.Dong
 * @create 2019-05-26 10:45
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;


    @Test
    public void testAdd(){
        ProductCategory productCategory = new ProductCategory("男生",2);
        ProductCategory productCategory1 = productCategoryRepository.save(productCategory);
        Assert.assertNotNull(productCategory1);
    }

    @Test
    public void testUpdate(){
        Optional<ProductCategory> category = productCategoryRepository.findById(9);
        ProductCategory productCategory = null;
        if (category.isPresent()) {
            productCategory = category.get();
            productCategory.setCategoryName("女生");
        }
        ProductCategory productCategory1 = productCategoryRepository.save(productCategory);
        Assert.assertNotNull(productCategory1);
    }

    @Test
    public void testFindAll(){

        List<ProductCategory> all = productCategoryRepository.findAll();

        System.out.println(all.toString());

    }

}
