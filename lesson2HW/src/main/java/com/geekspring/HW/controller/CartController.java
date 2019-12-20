package com.geekspring.HW.controller;

import com.geekspring.HW.utils.Cart;
import com.geekspring.HW.utils.CartItem;
import com.geekspring.HW.service.CartService;
import com.geekspring.HW.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@Log4j2
@RequestMapping("/cart")
public class CartController {
    private ProductService productService;
    private CartService cartService;

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

    @GetMapping("")
    public String getCart(Model model){
        Cart cart = cartService.getCart();
        List<CartItem> cartItems = new ArrayList<>(cart.getCartItems().values());
        model.addAttribute("list",cartItems);
        return "cart";
    }

}
