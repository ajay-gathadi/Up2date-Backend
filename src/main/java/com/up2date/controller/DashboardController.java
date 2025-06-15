package com.up2date.controller;

import com.up2date.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    @GetMapping("/total-business-forDate")
    public ResponseEntity<Double> getTotalBusinessForDate(){
        try{
            double totalBusiness = dashboardService.calculateTotalBusinessForDate();
            return ResponseEntity.ok(totalBusiness);
        } catch (Exception e){
            System.err.println("Error calculating total business for today: " + e.getMessage());
            return  ResponseEntity.internalServerError().body(0.0);
        }
    }

    @GetMapping("/cash-collected-forDate")
    public ResponseEntity<Double> getCashCollectedForDate(){
        double cashCollected = dashboardService.getTotalCashForDate();
        return ResponseEntity.ok(cashCollected);
    }

    @GetMapping("/online-collected-forDate")
    public ResponseEntity<Double> getOnlineCollectedForDate(){
        double onlineCollected = dashboardService.getTotalOnlineForDate();
        return ResponseEntity.ok(onlineCollected);
    }
}
