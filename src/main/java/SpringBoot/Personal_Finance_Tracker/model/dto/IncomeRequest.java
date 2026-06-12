package SpringBoot.Personal_Finance_Tracker.model.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class IncomeRequest {

    @NotNull(message = "Income amount should not be null")
    private Double incomeAmount;
    
    @NotBlank(message =  "Income source should not be null, emtpy and not contains only whitespace characters")
    private String incomeSource;

    @NotNull(message = "Income date should not be null")
    private LocalDate incomeDate;
    
    public IncomeRequest(){

    }

    public IncomeRequest(Double incomeAmount, String incomeSource, LocalDate incomeDate){
        this.incomeAmount = incomeAmount;
        this.incomeSource = incomeSource;
        this.incomeDate = incomeDate;
    }

    public void setIncomeAmount(Double incomeAmount){
        this.incomeAmount = incomeAmount;
    }

    public Double getIncomeAmount(){
        return incomeAmount;
    }

    public void setIncomeSource(String incomeSource){
        this.incomeSource = incomeSource;
    }

    public String getIncomeSource(){
        return incomeSource;
    }

    public void setIncomeDate(LocalDate incomeDate){
        this.incomeDate = incomeDate;
    }

    public LocalDate getIncomeDate(){
        return incomeDate;
    }
}
