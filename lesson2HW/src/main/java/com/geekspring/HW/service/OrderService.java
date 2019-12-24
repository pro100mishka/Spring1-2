package com.geekspring.HW.service;

import com.geekspring.HW.entity.Order;
import com.geekspring.HW.entity.OrderItem;
import com.geekspring.HW.entity.User;
import com.geekspring.HW.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private CartService cartService;

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public Order getNewOrder(User user){
        Order order = new Order().setUser(user).setOrderItems(new ArrayList<>(cartService.getCart().getOrderItemMap().values()));
        order.getOrderItems().forEach(orderItem -> {
            orderItem.setOrder(order)
                    .setPrice(orderItem.getProduct().getCost())
                    .setTotalPrice(orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getCount())));
        });
        return order;
    }
}
