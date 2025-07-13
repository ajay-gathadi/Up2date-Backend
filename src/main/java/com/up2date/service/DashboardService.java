package com.up2date.service;

import com.up2date.dto.DashboardSummaryDTO;
import com.up2date.dto.EmployeeCommissionDTO;
import com.up2date.repository.CustomerServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    private final CustomerServiceRepository customerServiceRepository;

    @Autowired
    public DashboardService(CustomerServiceRepository customerServiceRepository){
        this.customerServiceRepository = customerServiceRepository;
    }

    public List<EmployeeCommissionDTO> getEmployeeCommissionByDateRange(LocalDate startDate, LocalDate endDate){
        List<Object[]> rawResults = customerServiceRepository.findEmployeeCommissionsByDateRange(startDate, endDate);

        return rawResults.stream().map(currentResult -> new EmployeeCommissionDTO(
                (String) currentResult[0],
                ((Number) currentResult[1]).doubleValue()
        )).collect(Collectors.toList());
    }

    public double calculateTotalBusinessForDate(LocalDate localDate){
        Double total = customerServiceRepository.getTotalBusinessForDate(localDate);
        return total != null ? total : 0.0;
    }

    public DashboardSummaryDTO getDashboardSummaryForDate(LocalDate localDate){
        Double totalBusiness = customerServiceRepository.getTotalBusinessForDate(localDate);
        Double cashCollected = customerServiceRepository.getTotalCashCollectedForDate(localDate);
        Double onlineCollected = customerServiceRepository.getTotalOnlineCollectedForDate(localDate);
        Double totalCommission = customerServiceRepository.getTotalCommissionForDate(localDate);

        double totalProfit=  (totalBusiness != null ? totalBusiness : 0.0) - (totalCommission != null ? totalCommission : 0.0);

        return new DashboardSummaryDTO(
                totalBusiness != null ? totalBusiness : 0.0,
                cashCollected != null ? cashCollected : 0.0,
                onlineCollected != null ? onlineCollected : 0.0,
                totalProfit
        );
    }

    public double getTotalCashForDate(LocalDate localDate){
        Double totalCash = customerServiceRepository.getTotalCashCollectedForDate(localDate);
        return totalCash != null ? totalCash : 0.0;
    }

    public double getTotalOnlineForDate(LocalDate localDate){
        Double totalOnline = customerServiceRepository.getTotalOnlineCollectedForDate(localDate);
        return totalOnline != null ? totalOnline : 0.0;
    }
}
