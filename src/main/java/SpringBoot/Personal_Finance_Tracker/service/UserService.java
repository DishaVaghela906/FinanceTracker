package SpringBoot.Personal_Finance_Tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import SpringBoot.Personal_Finance_Tracker.model.entity.UserEntity;
import SpringBoot.Personal_Finance_Tracker.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity getUserbyUserEmail(String userEmail){
        try{
            System.out.println("Method getUserByUserEmail: ");
            return userRepository.findByUserEmail(userEmail);
        }catch(Exception e){
            System.out.println("Exception getUserbyUserEmail: " + e.getMessage());
            return null;
        }
    }


    public boolean addUser(UserEntity user){
        try{
            System.out.println("Method : addUser");
            if(userRepository.existsByUserEmail(user.getUserEmail())){
                return false;
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }catch(Exception e){
            System.out.println("Exception addUser : " + e.getMessage());
            //e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException{
        try{
            System.out.println("Method loadUserByUsername : ");
            UserEntity user = getUserbyUserEmail(userEmail);
            return User 
                .builder()
                .username(userEmail)
                .password(user.getPassword())
                .build();
        }catch(Exception e){
            System.out.println("Exception loadUserByUsername : " + e.getMessage());
            return null;
        }
    }
}
