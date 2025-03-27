package com.staj.liquibasepractice.service.impl;

import com.staj.liquibasepractice.dto.response.OrderResponce;
import com.staj.liquibasepractice.entity.Order;
import com.staj.liquibasepractice.entity.Product;
import com.staj.liquibasepractice.exceptions.ProductNotFoundException;
import com.staj.liquibasepractice.repository.OrderRepository;
import com.staj.liquibasepractice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public List<OrderResponce> findOrdersByUserId(Long userId) {
        return orderRepository.findByUser_Id(userId).stream()
                .map(this::orderToResponse).toList();
    }

    //<<<<<<<<<<< PRIVATE METHODS >>>>>>>>>>>

    // Order -> OrderResponse(DTO)
    private OrderResponce orderToResponse(Order order){
        return new OrderResponce(
                order.getName(),
                order.getPrice(),
                order.getQuantity()
        );
    }
}
