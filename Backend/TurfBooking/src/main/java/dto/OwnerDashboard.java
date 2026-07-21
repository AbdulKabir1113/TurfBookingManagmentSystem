package dto;

import java.util.List;

public class OwnerDashboard {

    private int totalTurfs;
    private int todaysBookings;
    private int upcomingBookings;
    private double totalRevenue;

    private List<Booking> recentBookings;

    public OwnerDashboard() {
    }

    public int getTotalTurfs() {
        return totalTurfs;
    }

    public void setTotalTurfs(int totalTurfs) {
        this.totalTurfs = totalTurfs;
    }

    public int getTodaysBookings() {
        return todaysBookings;
    }

    public void setTodaysBookings(int todaysBookings) {
        this.todaysBookings = todaysBookings;
    }

    public int getUpcomingBookings() {
        return upcomingBookings;
    }

    public void setUpcomingBookings(int upcomingBookings) {
        this.upcomingBookings = upcomingBookings;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public List<Booking> getRecentBookings() {
        return recentBookings;
    }

    public void setRecentBookings(List<Booking> recentBookings) {
        this.recentBookings = recentBookings;
    }

}