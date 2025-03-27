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
        List<Order> orders = orderRepository.findByUser_Id(userId);
        return validateOrderList(orders).stream()
                .map(this::orderToResponse).toList();
    }

    //<<<<<<<<<<< PRIVATE METHODS >>>>>>>>>>>

    //checks if there are any null orders in a list
    private List<Order> validateOrderList(List<Order> orders){
        if (orders.isEmpty()) {
            throw new NoSuchElementException("no order found"); //TODO custom exception
        }else {
            return orders;
        }
    }

    // Order -> OrderResponse(DTO)
    private OrderResponce orderToResponse(Order order){
        return new OrderResponce(
                order.getName(),
                order.getPrice(),
                order.getQuantity()
        );
    }
}
