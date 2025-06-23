package com.up2date.controller;

import com.up2date.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
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
