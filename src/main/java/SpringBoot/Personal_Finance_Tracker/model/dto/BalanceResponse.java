package SpringBoot.Personal_Finance_Tracker.model.dto;

public class BalanceResponse {
    private Double totalIncome;
    private Double totalExpense;
    private Double balance;

    public BalanceResponse() {}

    public BalanceResponse(Double totalIncome, Double totalExpense) {
        this.totalIncome = totalIncome != null ? totalIncome : 0.0;
        this.totalExpense = totalExpense != null ? totalExpense : 0.0;
        this.balance = this.totalIncome - this.totalExpense;
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

    @Override
    public String toString() {
        return "BalanceResponse{" +
                "totalIncome=" + totalIncome +
                ", totalExpense=" + totalExpense +
                ", balance=" + balance +
                '}';
    }
}
