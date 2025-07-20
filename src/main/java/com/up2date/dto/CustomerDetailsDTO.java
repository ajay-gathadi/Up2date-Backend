package com.up2date.dto;

/**
 * Data Transfer Object for Customer Details.
 * This DTO is used to transfer customer details including name, mobile number,
 * total amount spent, services availed, and the name of the employee who assisted.
 */
public record CustomerDetailsDTO(
    String customerName,
    String mobileNumber,
    double totalAmount,
    String services,
    String employeeName
) {
}
