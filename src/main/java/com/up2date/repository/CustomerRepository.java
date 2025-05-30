package com.up2date.repository;

import com.up2date.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByCustomerNameContainingIgnoreCase(String name);
}
