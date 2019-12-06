package com.geekspring.HW.controller;

import com.geekspring.HW.entity.Product;
import com.geekspring.HW.entity.User;
import com.geekspring.HW.service.ProductFilterAndPageService;
import com.geekspring.HW.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductFilterAndPageService productFilterAndPageService;
    private ProductService productService;

    @Autowired
    public void setProductFilterAndPageService(ProductFilterAndPageService productFilterAndPageService) {
        this.productFilterAndPageService = productFilterAndPageService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String getProducts(Model model,
            @RequestParam(value = "min") Optional<Double> min,
            @RequestParam(value = "max") Optional<Double> max,
            @RequestParam(value = "page") Optional<Integer> page,
            @RequestParam(value = "size") Optional<Integer> size){
        Specification<Product> specification = productFilterAndPageService.getSpecification(min,max);
        PageRequest pageRequest = productFilterAndPageService.getPageRequest(page,size);
        Page<Product> products = productService.findAllByPagingAndFiltering(specification,pageRequest);
        model.addAttribute("products",products);
        model.addAttribute("filter",productFilterAndPageService.getFilterForPage());
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
        Product product = productService.findById(id);
        if (product == null) return "errorForFind";
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(name = "id", required = false) Long id){
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        return "editProduct";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute(name = "product") Product product){
        productService.update(product);
        return "redirect:/product/findById?id="+product.getId();
    }

}
