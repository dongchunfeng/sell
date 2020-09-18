package com.itzixue.service;

import com.itzixue.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;

/**
 * @author Mr.Dong
 * @create 2019-05-30 10:48
 */
public interface PayService {

    PayResponse create(OrderDTO orderDTO);
    PayResponse notify(String notifyData);
}
