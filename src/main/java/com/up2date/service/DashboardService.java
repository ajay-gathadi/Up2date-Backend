package com.up2date.service;

import com.up2date.dto.DashboardSummaryDTO;
import com.up2date.repository.CustomerServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DashboardService {
    private final CustomerServiceRepository customerServiceRepository;

    @Autowired
    public DashboardService(CustomerServiceRepository customerServiceRepository){
        this.customerServiceRepository = customerServiceRepository;
    }

    public double calculateTotalBusinessForDate(){
        LocalDate today = LocalDate.now();
        Double total = customerServiceRepository.getTotalBusinessForDate();
        return total != null ? total : 0.0;
    }

    public DashboardSummaryDTO getDashboardSummaryForDate(){
        Double totalBusiness = customerServiceRepository.getTotalBusinessForDate();
        Double cashCollected = customerServiceRepository.getTotalCashCollectedForDate();
        Double onlineCollected = customerServiceRepository.getTotalOnlineCollectedForDate();

        return new DashboardSummaryDTO(
                totalBusiness != null ? totalBusiness : 0.0,
                cashCollected != null ? cashCollected : 0.0,
                onlineCollected != null ? onlineCollected : 0.0
        );
    }

    public double getTotalCashForDate(){
        Double totalCash = customerServiceRepository.getTotalCashCollectedForDate();
        return totalCash != null ? totalCash : 0.0;
    }

    public double getTotalOnlineForDate(){
        Double totalOnline = customerServiceRepository.getTotalOnlineCollectedForDate();
        return totalOnline != null ? totalOnline : 0.0;
    }
}
