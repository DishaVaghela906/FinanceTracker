package SpringBoot.Personal_Finance_Tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain basicAuth(HttpSecurity http){
        try{
            return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> 
                    auth.requestMatchers("/users/**")
                    .permitAll())
                .build();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        try{
            return new BCryptPasswordEncoder();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}
