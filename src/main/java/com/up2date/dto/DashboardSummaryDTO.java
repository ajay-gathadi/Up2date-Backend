package com.up2date.dto;

public class DashboardSummaryDTO {
    private double totalBusiness;
    private double cashCollected;
    private double onlineCollected;

    public DashboardSummaryDTO() {
    }

    public DashboardSummaryDTO(double totalBusiness, double cashCollected, double onlineCollected) {
        this.totalBusiness = totalBusiness;
        this.cashCollected = cashCollected;
        this.onlineCollected = onlineCollected;
    }

    public double getTotalBusiness() {
        return totalBusiness;
    }

    public void setTotalBusiness(double totalBusiness) {
        this.totalBusiness = totalBusiness;
    }

    public double getCashCollected() {
        return cashCollected;
    }

    public void setCashCollected(double cashCollected) {
        this.cashCollected = cashCollected;
    }

    public double getOnlineCollected() {
        return onlineCollected;
    }

    public void setOnlineCollected(double onlineCollected) {
        this.onlineCollected = onlineCollected;
    }
}
