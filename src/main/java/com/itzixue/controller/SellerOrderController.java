package com.itzixue.controller;

import com.itzixue.dto.OrderDTO;
import com.itzixue.enums.ResultEnum;
import com.itzixue.exception.SellException;
import com.itzixue.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

/**
 * 卖家订单表现层
 * @author Mr.Dong
 * @create 2019-05-30 15:05
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size,
                             Map<String,Object> map){
        Pageable pageable = PageRequest.of(page-1,size);
        Page<OrderDTO> orderDTOS = orderService.findList(pageable);
        map.put("orderDTOPages",orderDTOS);
        map.put("currPage",page);
        map.put("size",size);
        return new ModelAndView("order/list",map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【卖家端取消订单】 发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        OrderDTO orderDTO = null;
        try {
            orderDTO = orderService.findOne(orderId);
        }catch (SellException e){
            log.error("【卖家端详情订单】 发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);
    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("【卖家端完结订单】 发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success");
    }

}
