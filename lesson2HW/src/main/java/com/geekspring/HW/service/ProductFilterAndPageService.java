package com.geekspring.HW.service;

import com.geekspring.HW.entity.Product;
import com.geekspring.HW.repository.specification.ProductSpecifications;
import com.geekspring.HW.service.filter.FilterForPage;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class ProductFilterAndPageService {

    private FilterForPage filterForPage;

    @Autowired
    public void setFilterForPage(FilterForPage filterForPage) {
        this.filterForPage = filterForPage;
    }

    public Specification<Product> getSpecification(Optional<Double> min, Optional<Double> max){
        Specification<Product> temp = Specification.where(null);
        Double minLocal = min.orElse(null);
        Double maxLocal = max.orElse(null);
        if (minLocal!=null & temp!=null){
            temp = temp.and(ProductSpecifications.costGreaterThanOrEq(minLocal));
            filterForPage.setMin(minLocal);
        } else filterForPage.setMin(null);
        if (maxLocal!=null & temp!=null){
            filterForPage.setMax(maxLocal);
            temp = temp.and(ProductSpecifications.costLesserThanOrEq(maxLocal));
        } else filterForPage.setMax(null);
        return temp;
    }

    public PageRequest getPageRequest(Optional<Integer> page, Optional<Integer> size){
        int currentPage = page.orElse(1);
        int currentSize = size.orElse(5);
        return PageRequest.of(currentPage-1, currentSize, Sort.by(Sort.Direction.DESC,"cost"));
    }
}
