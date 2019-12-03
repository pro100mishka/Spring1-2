package com.geekspring.lesson2HW.repository;

import com.geekspring.lesson2HW.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepository  extends JpaRepository <Product, Long> {
        Product findByTitle(String title);
}
