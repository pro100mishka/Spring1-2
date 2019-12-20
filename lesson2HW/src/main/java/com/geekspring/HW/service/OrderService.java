package com.geekspring.HW.service;

import com.geekspring.HW.entity.Order;
import com.geekspring.HW.entity.User;
import com.geekspring.HW.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
//        return orderRepository.save(order);
        return order; //Тут просто не успел доделать.
    }

    public Order getNewOrder(User user){
        return new Order()
                .setUser(user)
                .setOrderItems(new ArrayList<>(cartService.getCart().getOrderItemMap().values()));
    }
}
