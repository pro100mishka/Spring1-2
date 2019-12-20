package com.geekspring.HW.restapi;

import com.geekspring.HW.entity.Product;
import com.geekspring.HW.errors_handler.exceptions.InvalidStateOfObjectException;
import com.geekspring.HW.errors_handler.exceptions.NotFoundException;
import com.geekspring.HW.service.ProductFilterAndPageService;
import com.geekspring.HW.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/product/")
public class RestApiProductController {

    private ProductService service;
    private ProductFilterAndPageService specificationService;

    @Autowired
    public void setSpecificationService(ProductFilterAndPageService specificationService) {
        this.specificationService = specificationService;
    }


    @Autowired
    public void setService(ProductService service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public List<Product> getAll(HttpServletRequest request,
                                @RequestParam(value = "min") Optional<Double> min,
                                @RequestParam(value = "max") Optional<Double> max,
                                @RequestParam(value = "category") Optional<Integer> category){
        return service.findAllByFiltering(specificationService.getSpecification(min,max,category));
    }

    @GetMapping(value = "/{id}")
    public Product getById(@PathVariable Long id){
        return service.findById(id).orElseThrow(()->new NotFoundException("Product by id: "+id+" not found."));
    }

    @PostMapping(value = "/")
    public Product add(@RequestBody Product product){
        if (product.getId()!=null)
            throw new InvalidStateOfObjectException("The id field of the incoming object matters.");
        return service.update(product);
    }

    @PutMapping(value = "/{id}")
    public Product edit(@RequestBody Product product, @PathVariable Long id) {
        service.findById(id).orElseThrow(()->new NotFoundException("Product by id: "+id+" not found."));
        return service.update(product.setId(id));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
