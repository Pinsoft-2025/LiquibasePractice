package com.staj.liquibasepractice.service;

import com.staj.liquibasepractice.dto.request.OrderRequest;
import com.staj.liquibasepractice.dto.response.OrderResponce;
import com.staj.liquibasepractice.entity.Order;
import com.staj.liquibasepractice.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<OrderResponce>findOrdersByUserId(Long userId);
    Order createOrder(OrderRequest orderRequest); //buy product
    void deleteOrder(Long orderId);
}
