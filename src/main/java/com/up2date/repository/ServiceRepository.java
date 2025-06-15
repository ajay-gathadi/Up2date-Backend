package com.up2date.repository;

import com.up2date.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    // This interface is responsible for interacting with the database for service-related operations.
    // It will contain methods to perform CRUD operations on the services table.
    List<Service> findByForMaleIsTrue();
    List<Service> findByForFemaleIsTrue();

    @Query("SELECT s FROM Service s LEFT JOIN s.customerServices cs GROUP BY s.serviceId ORDER BY COUNT(cs) DESC")
    List<Service> findAllOrderByServiceUsageCount();
}
