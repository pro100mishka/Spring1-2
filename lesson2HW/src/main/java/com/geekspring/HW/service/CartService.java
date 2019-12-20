package com.geekspring.HW.service;

import com.geekspring.HW.utils.Cart;
import com.geekspring.HW.entity.OrderItem;
import com.geekspring.HW.entity.Product;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Data
@Log4j2
public class CartService {

    private Cart cart;

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void addToCart(Product product){
        log.info("Received product to add to cart: " + product);
        OrderItem temp = cart.getOrderItemMap().get(product);
        if (temp == null){
            temp = new OrderItem().setProduct(product).setCount(0);
        }
        temp.setCount(temp.getCount()+1);
        cart.getOrderItemMap().put(temp.getProduct(),temp);
        log.info(cart.toString());
    }

}
