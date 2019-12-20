package com.geekspring.HW.utils;


import com.geekspring.HW.entity.Product;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
@Log4j2
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    private Map<Product, CartItem> cartItems;

    @PostConstruct
    public void init(){
        log.info("Create HashMap!");
        cartItems = new HashMap<>();
    }

}
