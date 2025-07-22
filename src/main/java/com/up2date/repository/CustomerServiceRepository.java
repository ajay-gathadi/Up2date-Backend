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

    @Query(value =  "WITH ServiceAgg AS (" +
                        "SELECT " +
                            "csj.customer_service_id, " +
                            "STRING_AGG(s.service_name, ', ') AS aggregated_services " +
                        "FROM " +
                            "customer_service_junction csj " +
                        "JOIN " +
                            "services s ON csj.service_id = s.service_id " +
                        "GROUP BY csj.customer_service_id " +
                    ") "+
                    "SELECT " +
                        "c.customer_name AS customerName, " +
                        "c.mobile_number AS mobileNumber, " +
                        "COUNT(DISTINCT CAST(cs.service_taken_date as DATE)) AS totalVisits, " +
                        "COALESCE(SUM(cs.amount),0) AS totalAmount, " +
                        "STRING_AGG(DISTINCT sa.aggregated_services, ', ') AS services, " +
                        "MAX(cs.service_taken_date) AS lastVisitDate " +
                    "FROM " +
                        "customer c " +
                    "JOIN " +
                        "customer_service cs ON c.customer_id = cs.customer_id " +
                    "LEFT JOIN " +
                        "ServiceAgg sa ON cs.id = sa.customer_service_id "+
                    "WHERE " +
                        "CAST(cs.service_taken_date AS DATE) BETWEEN :startDate AND :endDate " +
                    "GROUP BY " +
                        "c.customer_id, c.customer_name, c.mobile_number " +
                    "ORDER BY " +
                        "totalAmount DESC", nativeQuery = true
    )
    List<Object[]> findCustomerSummaryByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    /**
     * Retrieves customer details for a specific date.
     *
     * @param date the date for which to retrieve customer details
     * @return a list of Object arrays containing customer information
     */
    @Query(value = "SELECT " +
                        "c.customer_name AS customerName, " +
                        "c.mobile_number AS mobileNumber, " +
                        "cs.amount AS totalAmount, " +
                        "STRING_AGG(s.service_name, ', ') AS services, " +
                        "e.employee_name AS employeeName " +
                    "FROM " +
                        "customer_service cs " +
                    "JOIN " +
                        "customer c ON cs.customer_id = c.customer_id " +
                    "JOIN " +
                        "employee e ON cs.employee_id = e.employee_id " +
                    "LEFT JOIN " +
                        "customer_service_junction csj ON cs.id = csj.customer_service_id " +
                    "LEFT JOIN " +
                        "services s ON csj.service_id = s.service_id " +
                    "WHERE " +
                        "CAST(cs.service_taken_date AS DATE) = :date " +
                    "GROUP BY " +
                        "cs.id, c.customer_name, c.mobile_number,  cs.amount, e.employee_name " +
                    "ORDER BY " +
                        "cs.service_taken_date DESC", nativeQuery = true)
    List<Object[]> findCustomerDetailsForADate(@Param("date") LocalDate date);
}
