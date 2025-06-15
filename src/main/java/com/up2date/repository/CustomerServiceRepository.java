package com.up2date.repository;

import com.up2date.entity.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerServiceRepository extends JpaRepository<CustomerService, Long> {
    @Query(value = "SELECT COALESCE(SUM(amount), 0) FROM customer_service  " +
            "WHERE DATE(service_taken_date) = CURRENT_DATE", nativeQuery = true)
    Double getTotalBusinessForDate();

    @Query(value = "SELECT COALESCE(SUM(cash_amount),0) FROM customer_service "+
    "WHERE DATE(service_taken_date) = CURRENT_DATE", nativeQuery = true)
    Double getTotalCashCollectedForDate();

    @Query(value = "SELECT COALESCE(SUM(online_amount),0) FROM customer_service "+
    "WHERE DATE(service_taken_date) = CURRENT_DATE", nativeQuery = true)
    Double getTotalOnlineCollectedForDate();
}
