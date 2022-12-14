package com.cafe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Autowired
   private UserDetailsService userDetailsService;

   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

   @Autowired
   private UnAuthorizedUserAuthenticationEntryPoint authenticationEntryPoint;

   @Autowired
   private SecurityFilter securityFilter;

   @Bean
   @Override
   protected AuthenticationManager authenticationManager() throws Exception {
      return super.authenticationManager();
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {

      auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http
            .csrf().disable() // Disabling CSRF as not using form based login
            .authorizeRequests()
            .antMatchers("/api/auth/login", "/api/meta/**").permitAll()
            .antMatchers(HttpMethod.POST, "/api/user").permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            // To Verify user from second request onwards............
            .and()
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
   }

}
