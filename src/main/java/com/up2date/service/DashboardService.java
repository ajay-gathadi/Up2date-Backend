package com.up2date.service;

import com.up2date.dto.CustomerSummaryDTO;
import com.up2date.dto.CustomerVisitLogDTO;
import com.up2date.dto.DashboardSummaryDTO;
import com.up2date.dto.EmployeeCommissionDTO;
import com.up2date.repository.CustomerServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public List<CustomerSummaryDTO> getCustomerSummaryByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Object[]> rawResults = customerServiceRepository.findCustomerSummaryByDateRange(startDate, endDate);
        return rawResults.stream().map(currentResult -> new CustomerSummaryDTO(
                (String) currentResult[0],
                (String) currentResult[1],
                ((Number) currentResult[2]).longValue(),
                ((Number) currentResult[3]).doubleValue(),
                (String) currentResult[4],
                (convertToLocalDate(currentResult[5]))
        )).collect(Collectors.toList());
    }

    public List<CustomerVisitLogDTO> getCustomerVisitLogByDateRange(LocalDate startDate, LocalDate endDate){
        return customerServiceRepository.findCustomerVisitLogsByDateRange(startDate, endDate);
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

    private LocalDate convertToLocalDate(Object date){
        if (date == null) {
            return null;
        }
        if(date instanceof LocalDate localDate) {
            return localDate;
        } else if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        } else if( date instanceof java.sql.Timestamp){
            return ((java.sql.Timestamp) date).toLocalDateTime().toLocalDate();
        }

        String dateString = date.toString();
        if (dateString.contains("T")){
            if(dateString.endsWith("Z")){
                dateString = dateString.substring(0, dateString.length() - 1);
            }
            return LocalDateTime.parse(dateString).toLocalDate();
        } else{
            return LocalDate.parse(dateString);
        }
    }
}
