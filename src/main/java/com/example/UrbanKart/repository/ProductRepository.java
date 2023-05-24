package com.example.UrbanKart.repository;

import com.example.UrbanKart.Enum.Category;
import com.example.UrbanKart.model.Product;
import com.example.UrbanKart.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategoryAndPrice(Category category, int price);

    List<Product> findByCategory(Category category);

    List<Product> findByCategoryAndSellerId(Category category, int sellerId);

    @Query(value = "SELECT * FROM product", nativeQuery = true)
    List<Product> findAllProducts();
}
