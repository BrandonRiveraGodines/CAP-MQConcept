package com.capgemini.sample.ldap.activedirectory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class OpenLdapSecurityConfig {
	@Bean
    @Order(1)
    public SecurityFilterChain openLdapFilterChain(HttpSecurity http) throws Exception {

        http
            .securityMatcher("/api/openldap/**")
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .authenticationProvider(openLdapAuthProvider())
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationProvider openLdapAuthProvider() {
        return new LdapAuthenticationProvider(openLdapAuthenticator());
    }

    @Bean
    public BindAuthenticator openLdapAuthenticator() {
        BindAuthenticator authenticator =
                new BindAuthenticator(openLdapContextSource());
        authenticator.setUserDnPatterns(new String[]{"uid={0},ou=people"});
        return authenticator;
    }

    @Bean
    public BaseLdapPathContextSource openLdapContextSource() {
        LdapContextSource source = new LdapContextSource();
        source.setUrl("ldap://localhost:389");
        source.setBase("dc=example,dc=com");
        source.setUserDn("cn=admin,dc=example,dc=com");
        source.setPassword("admin");
        return source;
    }
}
