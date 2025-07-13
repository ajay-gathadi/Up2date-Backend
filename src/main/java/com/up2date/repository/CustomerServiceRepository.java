package com.up2date.repository;

import com.up2date.entity.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CustomerServiceRepository extends JpaRepository<CustomerService, Long> {
    @Query(value = "SELECT COALESCE(SUM(amount), 0) FROM customer_service  " +
            "WHERE DATE(service_taken_date) = :date", nativeQuery = true)
    Double getTotalBusinessForDate(@Param("date")LocalDate date);

    @Query(value = "SELECT COALESCE(SUM(cash_amount),0) FROM customer_service "+
    "WHERE DATE(service_taken_date) = :date", nativeQuery = true)
    Double getTotalCashCollectedForDate(@Param("date") LocalDate date);

    @Query(value = "SELECT COALESCE(SUM(online_amount),0) FROM customer_service "+
    "WHERE DATE(service_taken_date) = :date", nativeQuery = true)
    Double getTotalOnlineCollectedForDate(@Param("date") LocalDate date);

    List<CustomerService> findByCommissionIsNull();

    @Query(value = "SELECT COALESCE(SUM(commission),0) FROM customer_service "+
    "WHERE DATE(service_taken_date) = :date", nativeQuery = true)
    Double getTotalCommissionForDate(@Param("date") LocalDate date);

    @Query(value = "SELECT e.employee_name as employeeName, " +
                   "COALESCE(SUM(cs.commission),0) as totalCommission " +
                   "FROM employee e " +
                   "LEFT JOIN customer_service cs ON e.employee_id = cs.employee_id " +
                   "AND DATE(cs.service_taken_date) >= :startDate AND DATE(cs.service_taken_date) <= :endDate " +
                   "GROUP BY e.employee_id, e.employee_name "+
                   "ORDER BY e.employee_name", nativeQuery = true)
    List<Object[]> findEmployeeCommissionsByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
