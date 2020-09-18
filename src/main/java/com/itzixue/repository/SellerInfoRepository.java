package com.itzixue.repository;

import com.itzixue.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mr.Dong
 * @create 2019-06-04 10:52
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openId);

}
