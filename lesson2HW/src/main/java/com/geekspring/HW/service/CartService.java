package com.geekspring.HW.service;

import com.geekspring.HW.common.Cart;
import com.geekspring.HW.common.CartItem;
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
        CartItem temp = cart.getCartItems().get(product);
        log.info("Cart item before: "+ temp);
        if (temp == null){
            temp = new CartItem().setProduct(product).setCount(0);
        }
        temp.setCount(temp.getCount()+1);
        log.info("Cart item after: " + cart.getCartItems().put(product,temp));
    }

}
