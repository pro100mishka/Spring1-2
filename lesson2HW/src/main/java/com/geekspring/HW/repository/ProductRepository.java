package com.geekspring.HW.repository;

import com.geekspring.HW.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepository  extends JpaRepository <Product, Long> {
        Product findByTitle(String title);
}
