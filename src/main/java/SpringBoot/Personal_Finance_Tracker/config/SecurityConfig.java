package SpringBoot.Personal_Finance_Tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import SpringBoot.Personal_Finance_Tracker.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain basicAuth(HttpSecurity http){
        try{
            System.out.println("Method : basicAuth");
            return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> 
                    auth.requestMatchers("/users/**")
                        .permitAll()
                    .anyRequest()
                    .authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
        }catch(Exception e){
            System.out.println("Exception basicAuth : " + e.getMessage());
            //e.printStackTrace();
            return null;
        }
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        try{
            System.out.println("Method : passwordEncode");
            return new BCryptPasswordEncoder();
        }catch(Exception e){
            System.out.println("Exception passwordEncoder : " + e.getMessage());
            //e.printStackTrace();
            return null;
        }
    }

    @Bean
    UserDetailsService userDetailsService(){
        try{
            System.out.println("UserDetailsService beam");
            return new UserService();
        }catch(Exception e){
            System.out.println("Exception userDetailsService: " + e.getMessage());
            return null;
        }
    }

    @Bean
    AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
        try{
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
            daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
            return new ProviderManager(daoAuthenticationProvider);
        }catch(Exception e){
            System.out.println("Exception authenticationManager : " + e.getMessage());
            return null;
        }
    }
}
