package com.up2date.dto;

import java.time.LocalDate;

/**
 * Data Transfer Object for summarizing customer information for the aggregated view.
 */
public record CustomerSummaryDTO(
        String customerName,
        String mobileNumber,
        Long totalVisits,
        Double totalAmount,
        String services,
        LocalDate lastVisitDate
) {
}
