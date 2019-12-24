package com.geekspring.HW.repository;

import com.geekspring.HW.entity.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends PagingAndSortingRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {
        Product findByTitle(String title);
}
