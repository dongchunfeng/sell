package com.itzixue.dataobject.mapper;

import com.itzixue.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Mr.Dong
 * @create 2019-06-12 21:35
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("categoryName","小董最爱1111");
        map.put("categoryType",145);
        mapper.insertByMap(map);
    }

    @Test
    public void insertByObject(){
        ProductCategory productCategory = new ProductCategory("小董最不爱",12);
        int i = mapper.insertByObject(productCategory);
        Assert.assertEquals(1,i);
    }

    @Test
    public void findByCategoryType(){
        ProductCategory productCategory= mapper.findByCategoryType(1);
        System.out.println(productCategory);
        Assert.assertNotNull(productCategory);
    }

}