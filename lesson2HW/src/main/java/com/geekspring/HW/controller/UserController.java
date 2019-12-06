package com.geekspring.HW.controller;

import com.geekspring.HW.entity.Product;
import com.geekspring.HW.entity.User;
import com.geekspring.HW.service.ProductService;
import com.geekspring.HW.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findById")
    public String findById(Model model, @RequestParam(name = "id", required = false) Long id){
        User user = userService.findById(id);
        if (user == null) return "errorForFind";
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/add")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute(name = "user") User user){
        if  (userService.save(user)==null) return "errorForAdd";
        return "redirect:/";
    }

    @GetMapping("/all")
    public String getAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("list", users);
        return "users";
    }

    @PostMapping("/addProduct")
    public String addProduct(HttpServletRequest request){
        Long productId = Long.valueOf(request.getParameter("productId"));
        Product product = productService.findById(productId);
        if (product==null) return "errorForFind";
        Long userId = Long.valueOf(request.getParameter("userId"));
        User user = userService.findById(userId);
        if (user==null) return "errorForFind";
        user.getProductList().add(product);
        userService.save(user);
        return "redirect:/user/findById?id="+user.getId();
    }

    @GetMapping("/deleteProduct")
    public String deleteProductFromList(@RequestParam(name = "productId", required = true) Long productId,
                                        @RequestParam(name = "userId", required = true) Long userId){
        Product product = productService.findById(productId);
        if (product == null) return "errorForFind";
        User user = userService.findById(userId);
        if (user==null) return "errorForFind";
        user.getProductList().remove(product);
        userService.save(user);
        return "redirect:/user/findById?id="+user.getId();
    }

}
