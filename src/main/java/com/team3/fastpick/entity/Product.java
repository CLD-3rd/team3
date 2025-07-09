package com.team3.fastpick.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pidx;
    @Column(unique = true)
    private String name;
    @Column(name="image_url", nullable = false)
    private String imageUrl;
}
