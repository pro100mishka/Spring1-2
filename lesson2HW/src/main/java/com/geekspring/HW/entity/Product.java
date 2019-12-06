package com.geekspring.HW.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity (name = "product")
@Data
@Accessors (chain = true)
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "cost")
    private int cost;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}';
    }
}
