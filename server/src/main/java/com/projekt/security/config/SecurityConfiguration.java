package com.projekt.security.config;

import com.projekt.security.auth.UserAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserAuthenticationProvider userAuthenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                //todo: do readme, wyjątek dla pracy lokalnej
                //todo: idempotentność - funkcja zawsze zwróci raki sam wynik niezależnie od stanu aplikacji - żeby się zaloguj i wyloguj obok siebie nie wyświetlało
                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers("/api/v1/auth/register", "/api/v1/auth/authenticate", "/api/v1/auth/login").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class);

        return http.build();
    }
}
