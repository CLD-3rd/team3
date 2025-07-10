package com.team3.fastpick.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "pidx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pidx;
    @Column(unique = true)
    private String name;
    @Column(name="image_url", nullable = false)
    private String imageUrl;
    @Column(name = "open", nullable = false)
    private Boolean open; 
}