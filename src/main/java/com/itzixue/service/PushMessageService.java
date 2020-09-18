package com.itzixue.service;

import com.itzixue.dto.OrderDTO;

/**
 * @author Mr.Dong
 * @create 2019-06-07 11:34
 */
public interface PushMessageService {

    void orderStatus(OrderDTO orderDTO);

}
