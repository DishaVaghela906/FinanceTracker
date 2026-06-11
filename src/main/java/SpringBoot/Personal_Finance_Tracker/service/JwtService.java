package SpringBoot.Personal_Finance_Tracker.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    
}
