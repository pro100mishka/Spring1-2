package com.geekspring.HW.service;

import com.geekspring.HW.common.Cart;
import com.geekspring.HW.common.CartItem;
import com.geekspring.HW.entity.Product;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Data
public class CartService {

    private Cart cart;

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public CartItem addToCart(Product product){
        System.out.println(cart);
        CartItem temp = cart.getCartItems().get(product);
        if (temp == null){
            temp = new CartItem().setProduct(product).setCount(0);
        }
        temp.setCount(temp.getCount()+1);
        cart.getCartItems().put(product,temp);
        return temp;
    }


}
