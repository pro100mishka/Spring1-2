package com.geekspring.HW.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekspring.HW.pojo.ItemToCart;
import com.geekspring.HW.utils.Cart;
import com.geekspring.HW.entity.OrderItem;
import com.geekspring.HW.service.CartService;
import com.geekspring.HW.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@Log4j2
@RequestMapping("/cart")
public class CartController {
    private ProductService productService;
    private CartService cartService;
    private ObjectMapper mapper;

    @Autowired
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public void addToCart(@RequestParam(name = "id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        productService.findById(id).ifPresent(product -> cartService.addToCart(product));
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/")
    public String getCart(Model model){
        Cart cart = cartService.getCart();
        List<OrderItem> orderItems = new ArrayList<>(cart.getOrderItemMap().values());
        model.addAttribute("list", orderItems);
        return "cart";
    }
    //Вот тут у меня проблема, я вроде все сделал, и получаю эти зачения, но есть ошибка,
    // в попытки открыть ссесион бин из вебсокета, т.е. он ругается, что ссесионные бины не доступны в вебсокетах?
    //А то вы показывали пример, через аякс, я подумал, что смогу сделать это через сокеты..
    //получается, что сервисы я спокойно могу использовать, а вот наш ссесионный бин, нет =(

    @MessageMapping("/add")
    public void getItem(String s) throws JsonProcessingException {
        ItemToCart itemToCart = mapper.readValue(s,ItemToCart.class);
        if (cartService.getCart()!=null){
            System.out.println(cartService.getCart());
            productService.findById(itemToCart.getId()).ifPresent(product -> cartService.addToCart(product));

        }
    }
}
