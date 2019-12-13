package com.geekspring.HW.common;


import com.geekspring.HW.entity.Product;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    private Map<Product, CartItem> cartItems;

    @PostConstruct
    public void init(){
        System.out.println("Create Map");
        cartItems = new HashMap<>();
    }

}
