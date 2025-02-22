package com.app.journeyjoy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private PasswordEncoder en;

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Autowired
    private CustomAuthenticationEntryPoint authEntry;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .csrf().disable()
            .authorizeRequests(authz -> authz
                .antMatchers("/products/view", "/users/signup", "/users/signin", "/users/userRegistration",
                        "/v*/api-doc*/**", "/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/users/searchDestination", "/users/createtour", "/users/addTourReview", 
                        "/users/makeBooking", "/users/gethotels", "/users/makePayment", 
                        "/users/createRazorpayOrder", "/users/payment/response").hasRole("CUSTOMER")
                .antMatchers("/Admin/getAllTour", "/Admin/{DeleteTourid}", "/Admin/getAllDestination", 
                        "/Admin/newDestination", "/Admin/deletedestination/{DeleteDestinationid}",
                        "/Admin/allhotel", "/Admin/newHotel", "/Admin/deletehotel/{hotelId}",
                        "/Admin/updatehotel", "/Admin/allBoking", "/Admin/allPayments", "/Admin/allReviews")
                        .hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
