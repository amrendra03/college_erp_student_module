package com.server.jwt;

import io.jsonwebtoken.ExpiredJwtException;

import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter  extends OncePerRequestFilter {

    private Logger log = LoggerFactory.getLogger(OncePerRequestFilter.class);
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestHeader = request.getHeader("Authorization");
        log.info("Header : {}", requestHeader);

        String username = null;
        String token =  null;

        if(requestHeader !=null && requestHeader.startsWith("Bearer")){

          token = requestHeader.substring(7);

          try{
              username =  this.jwtToken.getUsernameFromToken(token);
          }catch (ExpiredJwtException ex){
              log.info("Give jwt token is expired !!");
          }catch(MalformedJwtException ex){
              log.info("Some changed haas done in token !! Invalid Token");
          }catch (Exception ex){
              log.info("Internal issue !! : {}",ex.getMessage());
          }

        }else{
            log.info("Invalid Header value !!");
        }

        if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails  = this.userDetailsService.loadUserByUsername(username);
            Boolean validation = this.jwtToken.validateToken(token,userDetails);
            if(validation){
                UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }else{
                log.info("Validation fails !!");
            }
        }else{
            log.info("Processing Authentication Username is null stage JWT Filter");
        }

        filterChain.doFilter(request,response);
    }
}
