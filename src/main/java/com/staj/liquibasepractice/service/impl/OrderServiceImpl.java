package com.staj.liquibasepractice.service.impl;

import com.staj.liquibasepractice.dto.request.OrderRequest;
import com.staj.liquibasepractice.dto.response.OrderResponce;
import com.staj.liquibasepractice.entity.Order;
import com.staj.liquibasepractice.entity.Product;
import com.staj.liquibasepractice.exceptions.ProductNotFoundException;
import com.staj.liquibasepractice.repository.OrderRepository;
import com.staj.liquibasepractice.repository.ProductRepository;
import com.staj.liquibasepractice.repository.UserRepository;
import com.staj.liquibasepractice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
//    private final ProductRepository productRepository;

    @Override
    public List<OrderResponce> findOrdersByUserId(Long userId) {
        return orderRepository.findByUser_Id(userId).stream()
                .map(this::orderToResponse).toList();
    }

    //Buy product, add to the Orders list
    @Transactional
    @Override
    public Order createOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .price(orderRequest.price())
                .name(orderRequest.name())
                .quantity(orderRequest.quantity())
                .products(orderRequest.products())
                .user(userRepository.findById(orderRequest.userId()).orElseThrow(NoSuchElementException::new))
                .build();
        return orderRepository.save(order);
    }
//
//    @Transactional
//    @Override
//    public Order addProductToOrder(Long orderId, Product product) {
//        Order order = orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);
//        order.getProducts().add(product);
//        return orderRepository.save(order);
//    }
//
//    @Transactional
//    @Override
//    public Order deleteProductFromOrder(Long orderId, Long productId) {
//        Order order = orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);
//        order.getProducts().remove(productRepository.findById(productId).orElseThrow(NoSuchElementException::new));
//        return orderRepository.save(order);
//    }

    @Transactional
    @Override
    public void deleteOrder(Long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        }else {
            throw new NoSuchElementException("Order not found");
        }

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

    // OrderRequest(Dto) -> Order
//    private Order requestToOrder(OrderRequest orderRequest){
//        Order order = Order.builder()
//                .price(orderRequest.price())
//                .name(orderRequest.name())
//                .quantity(orderRequest.quantity())
//                .user(userRepository.findById(orderRequest.userId()).orElseThrow(NoSuchElementException::new))
//                .build();
//    }
}
