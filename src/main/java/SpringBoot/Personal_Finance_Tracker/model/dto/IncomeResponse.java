package SpringBoot.Personal_Finance_Tracker.model.dto;

import java.time.LocalDate;

public class IncomeResponse {

    private Long incomeId;
    private Double incomeAmount;
    private String incomeSource;
    private LocalDate incomeDate;

    public IncomeResponse() {
    }

    public IncomeResponse(Long incomeId, Double incomeAmount, String incomeSource, LocalDate incomeDate) {
        this.incomeId = incomeId;
        this.incomeAmount = incomeAmount;
        this.incomeSource = incomeSource;
        this.incomeDate = incomeDate;
    }

    public Long getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Long incomeId) {
        this.incomeId = incomeId;
    }

    public Double getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(Double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public String getIncomeSource() {
        return incomeSource;
    }

    public void setIncomeSource(String incomeSource) {
        this.incomeSource = incomeSource;
    }

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDate incomeDate) {
        this.incomeDate = incomeDate;
    }
}
