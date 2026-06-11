package SpringBoot.Personal_Finance_Tracker.model.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Income")
public class Income {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Income_id")
    private Long incomeId;

    @Column(name = "Income_amount")
    private Double incomeAmount;

    @Column(name = "Income_source")
    private String incomeSource;

    @Column(name = "Income_date")
    private Date incomeDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public Income(){

    }

    public Income(Double incomeAmount, String incomeSource, Date incomeDate){
        this.incomeAmount = incomeAmount;
        this.incomeSource = incomeSource;
        this.incomeDate = incomeDate;
    }

    public void setIncomeId(Long incomeId){
        this.incomeId = incomeId;
    }

    public Long getIncomeId(){
        return incomeId;
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

    public void setIncomeDate(Date incomeDate){
        this.incomeDate = incomeDate;
    }

    public Date getIncomeDate(){
        return incomeDate;
    }

    public void setUser(UserEntity user){
        this.user = user;
    }

    public UserEntity getUser(){
        return user;
    }
}
