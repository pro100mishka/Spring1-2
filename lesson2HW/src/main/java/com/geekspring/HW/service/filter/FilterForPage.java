package com.geekspring.HW.service.filter;

import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Data
public class FilterForPage {

    private Double min;
    private Double max;
    private StringBuilder stringBuilder;

    @PostConstruct
    public void init(){
        stringBuilder = new StringBuilder();
    }

    @Override
    public String toString() {
        stringBuilder.setLength(0);
        if (min!=null) stringBuilder.append("&min=").append(min);
        if (max!=null) stringBuilder.append("&max=").append(max);
        return stringBuilder.toString();
    }
}
