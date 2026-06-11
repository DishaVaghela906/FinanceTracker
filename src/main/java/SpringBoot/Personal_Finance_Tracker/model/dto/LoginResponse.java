package SpringBoot.Personal_Finance_Tracker.model.dto;

public class LoginResponse {
    
    private String token;
    private String message;

    public LoginResponse(){

    }

    public LoginResponse(String token, String message){
        this.token = token;
        this.message = message;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}

