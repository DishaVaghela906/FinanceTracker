package SpringBoot.Personal_Finance_Tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import SpringBoot.Personal_Finance_Tracker.model.entity.User;
import SpringBoot.Personal_Finance_Tracker.repository.UserRepository;

@Service
public class UserService {
    
    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
