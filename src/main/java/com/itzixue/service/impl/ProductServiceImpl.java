package com.itzixue.service.impl;

import com.itzixue.dataobject.ProductInfo;
import com.itzixue.dto.CartDTO;
import com.itzixue.enums.ProductStatusEnum;
import com.itzixue.enums.ResultEnum;
import com.itzixue.exception.SellException;
import com.itzixue.repository.ProductInfoRepository;
import com.itzixue.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Mr.Dong
 * @create 2019-05-26 12:54
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo findOne(String productId) {
        Optional<ProductInfo> productInfo = productInfoRepository.findById(productId);
        return productInfo.orElse(null);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    @Transactional
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> productInfo = productInfoRepository.findById(cartDTO.getProductId());
            if (!productInfo.isPresent()){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //加库存
            ProductInfo productInfo1 = productInfo.get();
            productInfo1.setProductStock(productInfo1.getProductStock()+cartDTO.getProductQuantity());
            productInfoRepository.save(productInfo1);

        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> productInfo = productInfoRepository.findById(cartDTO.getProductId());
            if (productInfo.isPresent()){
                ProductInfo productInfo1 = productInfo.get();
                Integer result = productInfo1.getProductStock()-cartDTO.getProductQuantity();
                if(result<0)
                    throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
                //减库存
                productInfo1.setProductStock(result);
                //保存
                productInfoRepository.save(productInfo1);
            }else{
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        Optional<ProductInfo> productInfo = productInfoRepository.findById(productId);
        if(productInfo.isPresent()){
            ProductInfo info = productInfo.get();
            //如果是上架抛异常
            if(info.getProductStatusEnum() == ProductStatusEnum.UP){
                throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
            }
            info.setProductStatus(ProductStatusEnum.UP.getCode());
            return productInfoRepository.save(info);
        }
        return null;
    }

    @Override
    public ProductInfo offSale(String productId) {
        Optional<ProductInfo> productInfo = productInfoRepository.findById(productId);
        if(productInfo.isPresent()){
            ProductInfo info = productInfo.get();
            //如果是下架抛异常
            if(info.getProductStatusEnum() == ProductStatusEnum.DOWN){
                throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
            }
            info.setProductStatus(ProductStatusEnum.DOWN.getCode());
            return productInfoRepository.save(info);
        }
        return null;
    }
}
