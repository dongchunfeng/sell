package com.itzixue.service;

import com.itzixue.dataobject.ProductInfo;
import com.itzixue.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Mr.Dong
 * @create 2019-05-26 12:52
 */
public interface ProductService {

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo findOne(String productId);

    List<ProductInfo> findUpAll();

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);


    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);
}
