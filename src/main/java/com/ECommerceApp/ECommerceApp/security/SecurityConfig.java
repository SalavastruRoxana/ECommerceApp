package com.ECommerceApp.ECommerceApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // user
    //password: a295c421-9d35-4e65-9329-f8e93215d12c

    CustomerDetailsService customerDetailsService;
    private final JWTAuthEntryPoint authEntryPoint;

    @Autowired
    public SecurityConfig(CustomerDetailsService customerDetailsService, JWTAuthEntryPoint authEntryPoint) {
        this.customerDetailsService = customerDetailsService;
        this.authEntryPoint = authEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizationManagerRequestMatcherRegistry ->
                        {
                                authorizationManagerRequestMatcherRegistry
                                        .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                                        .requestMatchers("/user/**").hasAnyRole("CUSTOMER")
                                        .requestMatchers("/login/**").permitAll()
                                        .requestMatchers("/logout/**").permitAll()
                                        .requestMatchers("/api/auth/**").permitAll()
                                        //.exceptionHandling()
                                        //.authenticationEntryPoint(authEntryPoint)
                                        //.sessionManagement()
                                        //.and()
                                        .anyRequest().authenticated();
                        }
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtAuthenticatorFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JWTAuthenticatorFilter jwtAuthenticatorFilter() {
        return new JWTAuthenticatorFilter();
    }


    @Bean
    public AuthenticationManager authorizationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
