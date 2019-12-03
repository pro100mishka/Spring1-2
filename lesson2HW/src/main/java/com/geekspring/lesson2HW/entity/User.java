package com.geekspring.lesson2HW.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Data
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title", unique = true)
    private String title;

    @ManyToMany
    @JoinTable(
            name = "user_product",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> productList;

}
