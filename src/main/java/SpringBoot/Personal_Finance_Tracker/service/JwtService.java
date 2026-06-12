package SpringBoot.Personal_Finance_Tracker.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    private Key getSignedKey(){
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String userEmail){
        return Jwts
            .builder()
            .setSubject(userEmail)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
            .signWith(getSignedKey(), SignatureAlgorithm.HS384)
            .compact();
    }

    public Claims verifyTokenAndExtractClaims(String token){
        try{
            System.out.println("Method verifyTokenAndExtractClaims: ");
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
            System.out.println("Claims: " + claims);
            return claims;
        }catch(Exception e){
            System.out.println("Exception verifyTokenAndExtractClaims : " + e.getMessage());
            return null;
        }
    }  
    
    public Date getExpiration(String token){
        try{
            System.out.println("Method getExpiration: ");
            Date expiration = verifyTokenAndExtractClaims(token).getExpiration();
            System.out.println("Expiration of token : " + expiration);
            return expiration;
        }catch(Exception e){
            System.out.println("Exception getExpiration: " + e.getMessage());
            return null;
        }
    }

    public boolean isTokenExpired(String token){
        try{
            System.out.println("Method isTOkenExpired :");
            boolean isExpired = getExpiration(token).before(new Date());
            System.out.println("is Token expired: " + isExpired);
            return isExpired;
        }catch(Exception e){
            System.out.println("Exception isTokenExpired : " + e.getMessage());
            return false;
        }
    }
}
