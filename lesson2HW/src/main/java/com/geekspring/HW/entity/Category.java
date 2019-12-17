package com.geekspring.HW.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
