package com.FashionblogMbadady.FashionBlog.security;//package com.FashionblogMbadady.FashionBlog.security;


import com.FashionblogMbadady.FashionBlog.customException.BlogApiException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationMs;


    // Method to generate a token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + jwtExpirationMs);


       String token = Jwts.builder()
               .setSubject(username)
               .setIssuedAt(currentDate)
               .setExpiration(expiryDate)
               .signWith(SignatureAlgorithm.HS512, jwtSecret)
               .compact();

       return token;

    }

    // Method to generate username
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();


    }

    // validate the token
    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException exception) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
        } catch (MalformedJwtException exception) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException exception) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException exception) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException exception) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
        }
    }
}



//import com.FashionblogMbadady.FashionBlog.customException.BlogApiException;
//import io.jsonwebtoken.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;

//@Component
//public class JwtTokenProvider {
//
//    @Value("${app.jwt-secret}")
//    private String jwtSecret;
//
//    @Value("${app.jwt-expiration-milliseconds}")
//    private int jwtExpirationMs;
//
//    // Method to Generate a Token
//
//    public String generateToken(Authentication authentication){
//        String username = authentication.getName();
//        Date currentDate = new Date();
//        Date expiryDate = new Date(currentDate.getTime() + jwtExpirationMs);
//
//        String token = Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(currentDate)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
//
//        return token;
//    }
//
//    // Generate Username from the Token we created
//
//    public String getUsernameFromJwt(String token){
//        Claims claims = Jwts.parser()
//                .setSigningKey(jwtSecret)
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//    }
//
//    // to validate token
//
//    public Boolean validateToken(String token){
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//            return true;
//        }catch(SignatureException exception){
//            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
//        }catch (MalformedJwtException exception){
//            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
//        }catch (ExpiredJwtException exception){
//            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
//        }catch (UnsupportedJwtException exception){
//            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
//        }catch (IllegalArgumentException exception){
//            throw new BlogApiException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
//        }
//    }
//}
//
