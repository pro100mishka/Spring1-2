package com.geekspring.HW.service.filter;

import com.geekspring.HW.entity.Category;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Data
@Accessors(chain = true)
public class FilterForPage {

    private Double min;
    private Double max;
    private Integer page;
    private Integer size;
    private Integer category;

    private StringBuilder stringBuilder;

    @PostConstruct
    public void init(){
        stringBuilder = new StringBuilder();
    }

    @Override
    public String toString() {
        stringBuilder.setLength(0);
        if (category!=null) stringBuilder.append("&category=").append(category);
        if (page!=null) stringBuilder.append("&page=").append(page);
        if (size!=null) stringBuilder.append("&size").append(size);
        if (min!=null) stringBuilder.append("&min=").append(min);
        if (max!=null) stringBuilder.append("&max=").append(max);
        return stringBuilder.toString();
    }
}
