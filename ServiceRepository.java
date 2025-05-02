package com.up2date.repository;

import com.up2date.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    // This interface is responsible for interacting with the database for service-related operations.
    // It will contain methods to perform CRUD operations on the services table.
    // For example, you can use Spring Data JPA to create a repository interface like this:
    List<Service> findByForMaleIsTrue();
    List<Service> findByForFemaleIsTrue();
}
