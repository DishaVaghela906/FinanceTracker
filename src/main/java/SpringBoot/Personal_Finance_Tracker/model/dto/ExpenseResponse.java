package SpringBoot.Personal_Finance_Tracker.model.dto;

import java.time.LocalDate;

public class ExpenseResponse {

    private Long expenseId;
    private Double expenseAmount;
    private String expenseCategory;
    private LocalDate expenseDate;

    public ExpenseResponse(){

    }

    public ExpenseResponse(Long expenseId, Double expenseAmount, String expenseCategory, LocalDate expenseDate) {
        this.expenseId = expenseId;
        this.expenseAmount = expenseAmount;
        this.expenseCategory = expenseCategory;
        this.expenseDate = expenseDate;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }
}