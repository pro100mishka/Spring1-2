package com.geekspring.HW.service;

import com.geekspring.HW.entity.Product;
import com.geekspring.HW.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product){
        Product temp = productRepository.findByTitle(product.getTitle());
        if (temp!=null) return null;
        return productRepository.save(product);
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public Product update(Product product){
        return productRepository.save(product);
    }

    public Page<Product> findAllByPagingAndFiltering(Specification<Product> specification, Pageable pageable){
        return productRepository.findAll(specification,pageable);
    }

}
