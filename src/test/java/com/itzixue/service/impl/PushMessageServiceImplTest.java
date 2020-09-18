package com.itzixue.service.impl;

import com.itzixue.dto.OrderDTO;
import com.itzixue.service.OrderService;
import com.itzixue.service.PushMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Mr.Dong
 * @create 2019-06-07 11:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PushMessageService pushMessageService;

    @Test
    public void orderStatus() {
        OrderDTO orderDTO = orderService.findOne("1559196729726651171");
        pushMessageService.orderStatus(orderDTO);
    }
}