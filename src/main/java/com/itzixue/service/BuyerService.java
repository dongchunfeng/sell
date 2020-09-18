package com.itzixue.service;

import com.itzixue.dto.OrderDTO;

/**
 * 买家service
 * @author Mr.Dong
 * @create 2019-05-29 15:00
 */
public interface BuyerService {

    //查询某个订单
    OrderDTO findOrderOne(String openId,String orderId);

    //取消订单
    OrderDTO cancelOrder(String openId,String orderId);
}
