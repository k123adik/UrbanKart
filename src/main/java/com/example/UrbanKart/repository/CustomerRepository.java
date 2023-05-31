package com.example.UrbanKart.repository;

import com.example.UrbanKart.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT * FROM customer WHERE gender = 'FEMALE' AND age BETWEEN '20' AND '30'", nativeQuery = true)
    List<Customer> femaleCustomersAgeBetween20And30();

    @Query(value = "SELECT * FROM customer WHERE gender = 'MALE' AND age < '45'", nativeQuery = true)
    List<Customer> maleCustomersAgeLessThan45();

    Customer findByEmailId(String emailId);

}
