package com.staj.liquibasepractice.controller;

import com.staj.liquibasepractice.dto.request.OrderRequest;
import com.staj.liquibasepractice.dto.response.OrderResponce;
import com.staj.liquibasepractice.entity.Order;
import com.staj.liquibasepractice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order/user")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/find-user-orders/{id}")
    public ResponseEntity<List<OrderResponce>> findOrdersByUserId(@PathVariable("id") Long userId){
        return new ResponseEntity<>(orderService.findOrdersByUserId(userId), HttpStatus.FOUND);
    }

    @PostMapping("/new-order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest){
        return new ResponseEntity<>(orderService.createOrder(orderRequest), HttpStatus.CREATED);
    }

    //this cancels order in a way too simple way, I don't think you get a refund yet
    @DeleteMapping("/cancel-order/{id}")
    public String deleteOrder(@PathVariable("id") Long orderId){
        orderService.deleteOrder(orderId);
        return "Order deleted successfully";
    }
}
