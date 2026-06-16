package SpringBoot.Personal_Finance_Tracker.model.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ExpenseRequest {
   
    @NotNull(message = "Expense amount should not be null")
    private Double expenseAmount;

    @NotBlank(message = "Expense Category should not be null, empty and not contain only whitespace charater")
    private String expenseCategory;

    @NotNull(message = "Expense Date should not be null")
    private LocalDate expenseDate;

    public ExpenseRequest(){

    }

    public ExpenseRequest(Double expenseAmount, String expenseCategory, LocalDate expenseDate){
        this.expenseAmount = expenseAmount;
        this.expenseCategory = expenseCategory;
        this.expenseDate = expenseDate;
    }

    public void setExpenseAmount(Double expenseAmount){
        this.expenseAmount = expenseAmount;
    }

    public Double getExpenseAmount(){
        return expenseAmount;
    }

    public void setExpenseCategory(String expenseCategory){
        this.expenseCategory = expenseCategory;
    }

    public String getExpenseCategory(){
        return expenseCategory;
    }

    public void setExpenseDate(LocalDate expenseDate){
        this.expenseDate = expenseDate;
    }

    public LocalDate getExpenseDate(){
        return expenseDate;
    }
}
