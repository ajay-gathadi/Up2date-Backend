package com.up2date.repository;

import com.up2date.dto.CustomerSummaryDTO;
import com.up2date.dto.CustomerVisitLogDTO;
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

    @Query(value =
                    "SELECT " +
                        "e.employee_name AS employeeName, " +
                        "COALESCE(SUM(cs.commission),0) AS totalCommission " +
                    "FROM " +
                        "employee e " +
                    "LEFT JOIN " +
                        "customer_service cs ON e.employee_id = cs.employee_id " +
                    "AND " +
                        "CAST(cs.service_taken_date AS DATE) BETWEEN :startDate AND :endDate " +
                    "GROUP BY " +
                        "e.employee_id, e.employee_name "+
                    "ORDER BY " +
                        "e.employee_name", nativeQuery = true)
    List<Object[]> findEmployeeCommissionsByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT " +
                        "c.customer_name AS customerName, " +
                        "c.mobile_number AS mobileNumber, " +
                        "(COUNT(cs.id) AS totalVisits, " +
                        "COALESCE(SUM(cs.amount) AS totalAmount, " +
                        "STRING_AGG(DISTINCT s.service_name, ', ') AS services, " +
                        "MAX(cs.service_taken_date) AS lastVisitDate " +
                    "FROM " +
                        "customer c " +
                    "JOIN " +
                        "customer_service cs ON c.customer_id = cs.customer_id " +
                    "JOIN " +
                        "customer_service_junction csj ON cs.id = csj.customer_service_id " +
                    "JOIN " +
                        "services s ON csj.service_id = s.service_id " +
                    "WHERE " +
                        "CAST(cs.service_taken_date AS DATE) BETWEEN :startDate AND :endDate " +
                    "GROUP BY " +
                        "c.customer_id, c.customer_name, c.mobile_number " +
                    "ORDER BY " +
                        "totalAmount DESC", nativeQuery = true
    )
    List<Object[]> findCustomerSummaryByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT " +
                        "c.customer_name AS customerName, " +
                        "c.mobile_number AS mobileNumber, " +
                        "cs.service_taken_date AS serviceTakenDate, " +
                        "cs.amount AS amount, " +
                        "cs.payment_method AS paymentMethod, " +
                        "STRING_AGG(s.service_name, ', ') AS services " +
                   "FROM " +
                        "customer_service cs " +
                   "JOIN " +
                        "customer c ON cs.customer_id = c.customer_id " +
                   "JOIN " +
                        "customer_service_junction csj ON cs.id = csj.customer_service_id " +
                   "JOIN " +
                        "services s ON csj.service_id = s.service_id " +
                   "WHERE " +
                        "CAST(cs.service_taken_date AS DATE) BETWEEN :startDate AND :endDate "+
                   "GROUP BY " +
                        "cs.id ,c.customer_name, c.mobile_number, cs.service_taken_date, cs.amount, cs.payment_method "+
                   "ORDER BY "+
                        "cs.service_taken_date DESC", nativeQuery = true
    )
    List<CustomerVisitLogDTO> findCustomerVisitLogsByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
