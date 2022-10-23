//package com.FashionblogMbadady.FashionBlog.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private com.mbadady.springbootapiblog.security.JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//
//        //Get jwt (Token) from http request (Create a private method)
//        String token = getTokenFromRequest(request);
//
//        // validate token
//
//        if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)){
//            String username = jwtTokenProvider.getUsernameFromToken(token);
//
//            // load user associated with username
//            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
//
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//
//        }
//        filterChain.doFilter(request, response);
//
//    }
//    // Create a private to retrieve a token
//
//    // Bearer <AccessToken>
//
//    private String getTokenFromRequest(HttpServletRequest request){
//       String bearerToken = request.getHeader("Authorization");
//
//       if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("bearer ")){
//           return bearerToken.substring(7);
//       }
//       return null;
//    }
//}
package com.FashionblogMbadady.FashionBlog.security;

import com.FashionblogMbadady.FashionBlog.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {


    // inject the required dependencies
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //Get jwt (Token) from http request (Create a private method)
        String token = getJwtTokenFromRequest(request);
        // validate token
        if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)){
            // retrieve or get username from token
            String username = jwtTokenProvider.getUsernameFromToken(token);

            //load user associated with the token
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //set to spring security

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);

    }

    // private method to get jwt token

    // Bearer <accessToken>
    private String getJwtTokenFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");

        // checking if bearerToken is empty or not and whether bearerToken starts with Bearer
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            // we are extracting accessToken from Bearer <accessToken> line 46
            return bearerToken.substring(7);
        }
        return null;
    }
}

