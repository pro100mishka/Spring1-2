package com.geekspring.HW.repository.specification;

import com.geekspring.HW.entity.Product;
import org.springframework.data.jpa.domain.Specification;



public class ProductSpecifications {

    public static Specification<Product> costGreaterThanOrEq(Double value){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("cost"),value);
    }
    public static Specification<Product> costLesserThanOrEq(Double value){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("cost"),value);
    }
}
