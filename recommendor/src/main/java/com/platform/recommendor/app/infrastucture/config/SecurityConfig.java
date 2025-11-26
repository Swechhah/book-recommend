package com.platform.recommendor.app.infrastucture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws
            Exception{
        return config.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .headers(headers -> headers.frameOptions
                        (HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/email/send","/auth/discount/**","/api","/login","/h2-console/**", "/api/**", "/api/auth/**")
                                .permitAll()
                                .requestMatchers("/ws/**", "/ws-native/**").permitAll()
                                .requestMatchers("/test").authenticated()
//                                .requestMatchers("/admin/**").hasRole("SUPERADMIN")
//                                .requestMatchers("/org/**").hasAnyRole("ORG_ADMIN","EMPLOYEE","SUPERADMIN")
//                                .requestMatchers("/org-admin").hasAnyRole("ORG_ADMIN", "SUPERADMIN")
//                                .requestMatchers("/users/**").hasAnyRole("ORG_ADMIN","EMPLOYEE","SUPERADMIN","USER"
                                .anyRequest().permitAll());
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
