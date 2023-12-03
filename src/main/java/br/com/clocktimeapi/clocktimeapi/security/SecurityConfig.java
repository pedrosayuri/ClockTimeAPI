package br.com.clocktimeapi.clocktimeapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    
    @Autowired
    private SecurityUserFilter securityUserFilter;

    @Autowired
    private SecurityLoginFilter securityLoginFilter;

    // @Autowired
    // private SecurityWorkdayFilter securityWorkdayFilter;

    private static final String[] AUTH_WHITELIST = {
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/swagger-resources/**"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/login/").permitAll()
                .requestMatchers("/employee/read/**").permitAll()
                .requestMatchers(AUTH_WHITELIST).permitAll();
                
                auth.anyRequest().authenticated();
            })

        .addFilterBefore(securityLoginFilter, BasicAuthenticationFilter.class)
        .addFilterBefore(securityUserFilter, BasicAuthenticationFilter.class);
        // .addFilterBefore(securityWorkdayFilter, BasicAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
