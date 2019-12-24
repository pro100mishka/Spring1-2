package com.geekspring.HW.service;

import com.geekspring.HW.entity.Category;
import com.geekspring.HW.entity.Product;
import com.geekspring.HW.repository.ProductRepository;
import com.geekspring.HW.service.filter.FilterForPage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ProductService {

    private ProductFilterAndPageService productFilterAndPageService;
    private CategoryService categoryService;

    private ProductRepository productRepository;

    @Autowired
    public ProductService(CategoryService categoryService, ProductFilterAndPageService productFilterAndPageService, ProductRepository productRepository) {
        this.productFilterAndPageService = productFilterAndPageService;
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public Product save(Product product){
        Product temp = productRepository.findByTitle(product.getTitle());
        if (temp!=null) return null;
        return productRepository.save(product);
    }

    public Optional<Product> findById(Long id){
        log.info("Start find product by id: " + id);
        return productRepository.findById(id);
    }

    public Product update(Product product){
        return productRepository.save(product);
    }

    public Page<Product> findAllByPagingAndFiltering(Optional<Double> min, Optional<Double> max ,
                                                     Optional<Integer> page, Optional<Integer> size,
                                                     Optional<Integer> categoryId){
        Specification<Product> specification = productFilterAndPageService.getSpecification(min,max,categoryId);
        PageRequest pageRequest = productFilterAndPageService.getPageRequest(page,size);
        return productRepository.findAll(specification, pageRequest);
    }

    public List<Product> findAllByFiltering(Specification<Product> specification){
        return productRepository.findAll(specification);
    }

    public FilterForPage getFilter(){
        return productFilterAndPageService.getFilterForPage();
    }

    public List<Category> getAllCategory(){
        return categoryService.getAll();
    }

    public void delete(Long id){
        productRepository.findById(id).ifPresent(product -> productRepository.delete(product));
    }
}
