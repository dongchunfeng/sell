package com.itzixue.service.impl;

import com.itzixue.dto.OrderDTO;
import com.itzixue.enums.ResultEnum;
import com.itzixue.exception.SellException;
import com.itzixue.service.BuyerService;
import com.itzixue.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Dong
 * @create 2019-05-29 15:07
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openId, String orderId) {
        return checkOrderOwner(openId,orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openId, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openId, orderId);
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openId, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        //判断openId是否一致
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openId)){
            log.error("【查询订单】 订单openid不一致 openid={}, orderDTO={}",openId,orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
