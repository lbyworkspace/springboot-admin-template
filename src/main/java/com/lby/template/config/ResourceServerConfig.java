package com.lby.template.config;

import com.lby.template.handle.CustomAuthExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
    private CustomAuthExceptionHandler customAuthExceptionHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId("r1")
                .stateless(true)
                .tokenServices(tokenServices)
                .accessDeniedHandler(customAuthExceptionHandler)
                .authenticationEntryPoint(customAuthExceptionHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and().csrf().disable().exceptionHandling()
                .and().authorizeRequests().anyRequest().authenticated();
    }
}
