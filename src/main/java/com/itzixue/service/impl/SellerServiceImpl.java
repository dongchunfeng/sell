package com.itzixue.service.impl;

import com.itzixue.dataobject.SellerInfo;
import com.itzixue.repository.SellerInfoRepository;
import com.itzixue.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Dong
 * @create 2019-06-04 19:50
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellInfoByOpenid(String openId) {
        return repository.findByOpenid(openId);
    }
}
