package SpringBoot.Personal_Finance_Tracker.model.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Expense")
public class Expense {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Expense_id")
    private Long expenseId;

    @Column(name = "Expense_amount")
    private Double expenseAmount;

    @Column(name = "Expense_category")
    private String expenseCategory;

    @Column(name = "Expense_date")
    private Date expenseDate;

    public Expense(){

    }

    public Expense(Double expenseAmount, String expenseCategory, Date expenseDate){
        this.expenseAmount = expenseAmount;
        this.expenseCategory = expenseCategory;
        this.expenseDate = expenseDate;
    }

    public void setExpenseId(Long expenseId){
        this.expenseId = expenseId;
    }

    public Long getExpenseId(){
        return expenseId;
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

    public void setExpenseDate(Date expenseDate){
        this.expenseDate = expenseDate;
    }

    public Date getExpenseDate(){
        return expenseDate;
    }
}
