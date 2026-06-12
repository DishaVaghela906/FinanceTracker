package SpringBoot.Personal_Finance_Tracker.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import SpringBoot.Personal_Finance_Tracker.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
    
    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request , HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        try{
            System.out.println("Filter: doFilterInternal");
            String authHeader = request.getHeader("Authorization");
            String token = null;

            if(authHeader != null && authHeader.startsWith("Bearer")){
                token = authHeader.substring(7);
            }

            if(token != null && SecurityContextHolder.getContext().getAuthentication() == null){
                Claims claims = jwtService.verifyTokenAndExtractClaims(token);
                if(!jwtService.isTokenExpired(token)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, null);
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }else{
                    System.out.println("Token is expired");
                    return;
                }
            }
            filterChain.doFilter(request, response);
        }catch(Exception e){
            System.out.println("Exception doFilterInternal: " + e.getMessage());
        }
    }
}
