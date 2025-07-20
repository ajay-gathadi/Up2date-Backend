package com.up2date.controller;

import com.up2date.dto.CustomerDetailsDTO;
import com.up2date.dto.CustomerSummaryDTO;
import com.up2date.dto.DashboardSummaryDTO;
import com.up2date.dto.EmployeeCommissionDTO;
import com.up2date.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    @GetMapping("/customer-summary")
    public ResponseEntity<List<CustomerSummaryDTO>> getCustomerSummary(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ){
        try{
            List<CustomerSummaryDTO> customerSummaries = dashboardService.getCustomerSummaryByDateRange(startDate, endDate);
            return ResponseEntity.ok(customerSummaries);
        } catch (Exception e){
            System.err.println("Error fetching customer summary: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/customerDetailsForADate")
    public ResponseEntity<List<CustomerDetailsDTO>> getCustomerDetailsForADate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate
    ){
        try {
            List<CustomerDetailsDTO> customerDetails = dashboardService.getCustomerDetailsForADate(localDate);
            return ResponseEntity.ok(customerDetails);
        } catch (Exception e){
            System.err.println("Error fetching customer details for date: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/employee-commissions")
    public ResponseEntity<List<EmployeeCommissionDTO>> getEmployeeCommissions(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ){
        try{
            List<EmployeeCommissionDTO> commissionDTOS = dashboardService.getEmployeeCommissionByDateRange(startDate,endDate);
            return ResponseEntity.ok(commissionDTOS);
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryDTO> getDashboardSummary(@RequestParam(value = "date", required = false)
                                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> localDate){
        LocalDate queryDate = localDate.orElse(LocalDate.now());
        DashboardSummaryDTO summary = dashboardService.getDashboardSummaryForDate(queryDate);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/total-business-forDate")
    public ResponseEntity<Double> getTotalBusinessForDate(@RequestParam(value = "date", required = false)
                                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Optional<LocalDate> localDate){
        try{
            LocalDate queryDate = localDate.orElse(LocalDate.now());
            double totalBusiness = dashboardService.calculateTotalBusinessForDate(queryDate);
            return ResponseEntity.ok(totalBusiness);
        } catch (Exception e){
            System.err.println("Error calculating total business for today: " + e.getMessage());
            return  ResponseEntity.internalServerError().body(0.0);
        }
    }

    @GetMapping("/cash-collected-forDate")
    public ResponseEntity<Double> getCashCollectedForDate(@RequestParam(value = "date", required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> localDate){
        LocalDate queryDate = localDate.orElse(LocalDate.now());
        double cashCollected = dashboardService.getTotalCashForDate(queryDate);
        return ResponseEntity.ok(cashCollected);
    }

    @GetMapping("/online-collected-forDate")
    public ResponseEntity<Double> getOnlineCollectedForDate(@RequestParam(value = "date", required = false)
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> localDate){
        LocalDate queryDate = localDate.orElse(LocalDate.now());
        double onlineCollected = dashboardService.getTotalOnlineForDate(queryDate);

        return ResponseEntity.ok(onlineCollected);
    }
}
