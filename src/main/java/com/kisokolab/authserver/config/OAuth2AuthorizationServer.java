package com.kisokolab.authserver.config;

import com.kisokolab.authserver.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Autowired
    @Qualifier("authenticationManagerBean")
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    PasswordEncoder clientPasswordEncoder;


    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
//                .checkTokenAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(clientPasswordEncoder);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                 .tokenStore(tokenStore())
                 .authenticationManager(authenticationManager)
                 .userDetailsService(userDetailsService);
    }
}


