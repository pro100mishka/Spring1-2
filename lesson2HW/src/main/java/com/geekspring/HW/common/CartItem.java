package com.geekspring.HW.common;

import com.geekspring.HW.entity.Product;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CartItem {

    private Product product;

    private int count;
}
