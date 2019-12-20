package com.geekspring.HW.controller;


import com.geekspring.HW.entity.Address;
import com.geekspring.HW.entity.Order;
import com.geekspring.HW.entity.OrderItem;
import com.geekspring.HW.entity.User;
import com.geekspring.HW.service.CartService;
import com.geekspring.HW.service.OrderService;
import com.geekspring.HW.service.UserService;
import com.geekspring.HW.utils.Cart;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
@Log4j2
public class OrderController {
    private CartService cartService;
    private UserService userService;
    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getOrder(Principal principal, Model model){
        if (principal==null) return "index";
        User user = userService.findByPhone(principal.getName());
        if (user!=null){
            Cart cart = cartService.getCart();
            if (cart.getOrderItemMap()==null) return "error";
            Order order = orderService.getNewOrder(user);
            cart.setPrepareOrder(order);
            model.addAttribute("order",order);
            model.addAttribute("address", new Address().setPhone(user.getPhone()));
            return "order";
        }
        return "error";
    }

    @PostMapping("/")
    public String setOrder(@ModelAttribute("address") Address address){
        Order order = cartService.getCart().getPrepareOrder();
        order.setAddress(address);
        log.info("Order success: " + orderService.save(order));
        cartService.getCart().setPrepareOrder(null);
        return "redirect:/";
    }
}
