package com.team3.fastpick.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team3.fastpick.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
