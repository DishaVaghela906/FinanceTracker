package SpringBoot.Personal_Finance_Tracker.model.dto;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

public class ExpenseRequest {
   
    private Double expenseAmount;

    private String expenseCategory;

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
