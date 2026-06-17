package SpringBoot.Personal_Finance_Tracker.model.dto;

import java.util.Map;

public class MonthlyReportResponse {
    private Integer year;
    private Integer month;
    private Double totalIncome;
    private Double totalExpense;
    private Double balance;
    private Map<String, Double> expenseBreakdown; // Category -> Percentage

    public MonthlyReportResponse() {}

    public MonthlyReportResponse(Integer year, Integer month, Double totalIncome, Double totalExpense, Map<String, Double> expenseBreakdown) {
        this.year = year;
        this.month = month;
        this.totalIncome = totalIncome != null ? totalIncome : 0.0;
        this.totalExpense = totalExpense != null ? totalExpense : 0.0;
        this.balance = this.totalIncome - this.totalExpense;
        this.expenseBreakdown = expenseBreakdown;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome != null ? totalIncome : 0.0;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense != null ? totalExpense : 0.0;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Map<String, Double> getExpenseBreakdown() {
        return expenseBreakdown;
    }

    public void setExpenseBreakdown(Map<String, Double> expenseBreakdown) {
        this.expenseBreakdown = expenseBreakdown;
    }

    @Override
    public String toString() {
        return "MonthlyReportResponse{" +
                "year=" + year +
                ", month=" + month +
                ", totalIncome=" + totalIncome +
                ", totalExpense=" + totalExpense +
                ", balance=" + balance +
                ", expenseBreakdown=" + expenseBreakdown +
                '}';
    }
}
