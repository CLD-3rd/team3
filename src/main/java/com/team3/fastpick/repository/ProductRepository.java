package com.team3.fastpick.repository;

import com.team3.fastpick.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
