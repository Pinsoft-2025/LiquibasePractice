package com.staj.liquibasepractice.controller;

import com.staj.liquibasepractice.dto.request.OrderRequest;
import com.staj.liquibasepractice.dto.response.OrderResponce;
import com.staj.liquibasepractice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/user/find-user-orders/{id}")
    public ResponseEntity<List<OrderResponce>> findOrdersByUserId(@PathVariable("id") Long userId){
        return new ResponseEntity<>(orderService.findOrdersByUserId(userId), HttpStatus.FOUND);
    }
}
