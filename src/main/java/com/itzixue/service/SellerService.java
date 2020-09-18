package com.itzixue.service;

import com.itzixue.dataobject.SellerInfo;

/**
 * @author Mr.Dong
 * @create 2019-06-04 19:25
 */
public interface SellerService {

    /**
     * 通过openId查询卖家端用户信息
     * @param openId
     * @return
     */
    SellerInfo findSellInfoByOpenid(String openId);

}
