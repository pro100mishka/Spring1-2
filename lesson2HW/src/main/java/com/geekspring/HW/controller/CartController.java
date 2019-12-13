package com.geekspring.HW.controller;

import com.geekspring.HW.common.Cart;
import com.geekspring.HW.common.CartItem;
import com.geekspring.HW.entity.Product;
import com.geekspring.HW.service.CartService;
import com.geekspring.HW.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
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
    public String addToCart(@RequestParam(name = "id") Long id,
                            @RequestParam(value = "min") Optional<Double> min,
                            @RequestParam(value = "max") Optional<Double> max,
                            @RequestParam(value = "page") Optional<Integer> page,
                            @RequestParam(value = "size") Optional<Integer> size
    ){
        Product product = productService.findById(id);
        if (product!=null){
            System.out.println(cartService.addToCart(product));
        }
        return "redirect:/product/all";
    }

    @GetMapping("/")
    public String getCart(Model model){
        Cart cart = cartService.getCart();
        List<CartItem> cartItems = new ArrayList<>(cart.getCartItems().values());
        model.addAttribute("list",cartItems);
        return "cart";
    }

}
