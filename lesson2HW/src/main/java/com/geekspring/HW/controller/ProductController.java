package com.geekspring.HW.controller;

import com.geekspring.HW.entity.Product;
import com.geekspring.HW.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String getProducts(Model model,
                              @RequestParam(value = "min") Optional<Double> min,
                              @RequestParam(value = "max") Optional<Double> max,
                              @RequestParam(value = "page") Optional<Integer> page,
                              @RequestParam(value = "size") Optional<Integer> size,
                              @RequestParam(value = "category") Optional<Integer> category){
        Page<Product> products = productService.findAllByPagingAndFiltering(min, max, page, size, category);
        model.addAttribute("products",products);
        model.addAttribute("filter", productService.getFilter());
        model.addAttribute("categories", productService.getAllCategory());
        return "products";
    }

    @GetMapping("/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(name = "product") Product product){
        if  (productService.save(product)==null) return "errorForAdd";
        return "redirect:/";
    }

    @GetMapping("/findById")
    public String getById(Model model, @RequestParam(name = "id", required = false) Long id){
        Product product = productService.findById(id).orElse(null);
        if (product == null) return "errorForFind";
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(name = "id", required = false) Long id){
        Product product = productService.findById(id).orElse(null);
        model.addAttribute("product",product);
        return "editProduct";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute(name = "product") Product product){
        productService.update(product);
        return "redirect:/product/findById?id="+product.getId();
    }

}
