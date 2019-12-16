package com.geekspring.HW.service;

import com.geekspring.HW.entity.Product;
import com.geekspring.HW.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Product update(Product product){
        return productRepository.save(product);
    }

    public Page<Product> findAllByPagingAndFiltering(Specification<Product> specification, Pageable pageable){
        return productRepository.findAll(specification,pageable);
    }

    public List<Product> findAllByFiltering(Specification<Product> specification){
        return productRepository.findAll(specification);
    }

    public void delete(Long id){
        productRepository.findById(id).ifPresent(product -> productRepository.delete(product));
    }
}
