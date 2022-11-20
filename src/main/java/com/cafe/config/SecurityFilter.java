package com.cafe.config;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cafe.entities.User;
import com.cafe.services.UserService;
import com.cafe.utils.TokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

   @Autowired
   private TokenUtil tokenUtil;

   @Autowired
   private UserDetailsService userDetailsService;

   @Autowired
   private UserService userService;

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
         throws ServletException, IOException {

      // Reading Token from Authorization Header
      String authorization = request.getHeader("Authorization");
      // System.out.println(token);
      if (authorization != null) {
         String[] tokenOptional = authorization.split("(?:B|b)earer ");
         String token = tokenOptional[1];
         try {
            String username = tokenUtil.getSubject(token);
            // if username is not null & Context Authentication must be null
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
               User isUsernameExists = userService.findByLoginName(username);

               if (isUsernameExists != null) {
                  UserDetails user = userDetailsService.loadUserByUsername(username);
                  boolean isValid = tokenUtil.isValidToken(token, user.getUsername());
                  if (isValid) {
                     UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,
                           user.getPassword(), user.getAuthorities());
                     authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                     SecurityContextHolder.getContext().setAuthentication(authToken);
                  }
               }
            }
         } catch (MalformedJwtException | ExpiredJwtException e) {
            System.out.println(e);
         }
      }
      filterChain.doFilter(request, response);
   }

}
