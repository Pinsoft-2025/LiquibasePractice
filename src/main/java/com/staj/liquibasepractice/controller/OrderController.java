package com.staj.liquibasepractice.controller;

import com.staj.liquibasepractice.dto.request.OrderRequest;
import com.staj.liquibasepractice.dto.response.OrderResponce;
import com.staj.liquibasepractice.entity.Order;
import com.staj.liquibasepractice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order/user")
@Tag(name = "Order API", description = "Sipariş yönetimi ile ilgili işlemler")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/find-user-orders/{id}")
    @Operation(summary = "Kullanıcının siparişlerini getir", description = "Belirtilen kullanıcı ID'sine göre siparişleri getirir.")
    public ResponseEntity<List<OrderResponce>> findOrdersByUserId(@PathVariable("id") Long userId){
        return new ResponseEntity<>(orderService.findOrdersByUserId(userId), HttpStatus.FOUND);
    }

    @PostMapping("/new-order")
    @Operation(summary = "Yeni sipariş oluştur", description = "Yeni bir sipariş ekler.")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest){
        return new ResponseEntity<>(orderService.createOrder(orderRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/cancel-order/{id}")
    @Operation(summary = "Siparişi iptal et", description = "Belirtilen siparişi iptal eder (henüz geri ödeme sağlamaz).")
    public String deleteOrder(@PathVariable("id") Long orderId){
        orderService.deleteOrder(orderId);
        return "Order deleted successfully";
    }
}
