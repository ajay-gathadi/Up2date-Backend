package com.up2date.dto;

import java.time.LocalDate;

public record CustomerVisitLogDTO(
        String customerName,
        String mobileNumber,
        String services,
        Double amount,
        String paymentMethod,
        LocalDate serviceTakenDate
) {
}
