package com.up2date.dto;

import java.time.LocalDate;

public record CustomerDetailsDTO(
        String customerName,
        String mobileNumber,
        LocalDate serviceTakenDate,
        Long totalVisits,
        Double totalAmount,
        String services
) {
}
