package com.capgemini.sample.ldap.activedirectory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ActiveDirectorySecurityConfig {

	@Bean
    @Order(2)
    public SecurityFilterChain adFilterChain(HttpSecurity http) throws Exception {

        http
            .securityMatcher("/api/ad/**")
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .authenticationProvider(activeDirectoryAuthProvider())
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationProvider activeDirectoryAuthProvider() {
        return new ActiveDirectoryLdapAuthenticationProvider(
                "example.com",
                "ldap://ad.example.com:389"
        );
    }	
}
