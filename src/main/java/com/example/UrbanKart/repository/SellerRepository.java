package com.example.UrbanKart.repository;

import com.example.UrbanKart.Enum.Category;
import com.example.UrbanKart.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {

    Seller findByEmailId(String emailId);

    @Query(value = "SELECT * FROM seller", nativeQuery = true)
    List<Seller> getAllSellers();
}
