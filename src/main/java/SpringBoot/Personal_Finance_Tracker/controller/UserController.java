package SpringBoot.Personal_Finance_Tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SpringBoot.Personal_Finance_Tracker.model.dto.LoginRequest;
import SpringBoot.Personal_Finance_Tracker.model.dto.LoginResponse;
import SpringBoot.Personal_Finance_Tracker.model.dto.ResponseWrapper;
import SpringBoot.Personal_Finance_Tracker.model.entity.UserEntity;
import SpringBoot.Personal_Finance_Tracker.repository.UserRepository;
import SpringBoot.Personal_Finance_Tracker.service.JwtService;
import SpringBoot.Personal_Finance_Tracker.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<ResponseWrapper<String>> registerUser(@RequestBody @Valid UserEntity user){
        try{
            System.out.println("Method : registerUser");
            if(userService.addUser(user)){
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(ResponseWrapper.success("User registered successfully"));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ResponseWrapper.error("User already exists"));
            }
            
        }catch(Exception e){
            System.out.println("Exception  registerUser: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseWrapper.error("Unable to register user"));
        }
        
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseWrapper<LoginResponse>> loginUser(@RequestBody @Valid LoginRequest loginRequest){
        try{
            System.out.println("Method: LoginUser");
            
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserEmail(), loginRequest.getPassword()));
                LoginResponse loginResponse = new LoginResponse();

            if(authentication.isAuthenticated()){
                if(userRepository.existsByUserEmail(loginRequest.getUserEmail())){

                    UserEntity user = userRepository.findByUserEmail(loginRequest.getUserEmail());

                    if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
                        loginResponse.setToken(jwtService.generateToken(loginRequest.getUserEmail()));
                        loginResponse.setMessage("Login successful");
                    }else{
                        loginResponse.setToken(null);
                        loginResponse.setMessage("Invalid credentials");
                    }
                }else{
                    System.out.println(userRepository.existsByUserEmail(loginRequest.getUserEmail()));
                    loginResponse.setToken(null);
                    loginResponse.setMessage("User doesn't exist");
                }
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseWrapper.success(loginResponse, "Login processed"));
        }catch(Exception e){
            System.out.println("Exception loginUser: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseWrapper.error("Unable to process login"));
        }
    }
}

