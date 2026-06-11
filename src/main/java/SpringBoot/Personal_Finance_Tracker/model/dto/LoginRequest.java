package SpringBoot.Personal_Finance_Tracker.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    
    @NotBlank(message = "User email should not be null or empty or should not contains only whitespace characters.")
    @Email(message = "Invalid user Email")
    private String userEmail;

    @NotBlank(message = "User email should not be null or empty or should not contains only whitespace characters.")
    private String password;

    public LoginRequest(){

    }

    public LoginRequest(String userEmail, String password){
        this.userEmail = userEmail;
        this.password = password;
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

}
