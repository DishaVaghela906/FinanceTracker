package SpringBoot.Personal_Finance_Tracker.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name =  "User")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    @NotBlank(message =  "Username should not be null or empty or should not contains only whitespace characters.")
    private String userName;

    @Column(name = "user_email")
    @NotBlank(message = "User email should not be null or empty or should not contains only whitespace characters.")
    @Email(message = "Invalid user Email")
    private String userEmail;

    @Column(name = "password")
    @NotBlank(message = "password should not be null or empty or should not contains only whitespace characters.")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Income> incomes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Expense> expenses = new ArrayList<>();

    public UserEntity(){

    }

    public UserEntity(String userName, String userEmail, String password){
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public Long getUserId(){
        return userId;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setIncomes(List<Income> incomes){
        this.incomes = incomes;
    }

    public List<Income> getIncomes(){
        return incomes;
    }

    public void setExpenses(List<Expense> expenses){
        this.expenses = expenses;
    }

    public List<Expense> getExpenses(){
        return expenses;
    }
}
