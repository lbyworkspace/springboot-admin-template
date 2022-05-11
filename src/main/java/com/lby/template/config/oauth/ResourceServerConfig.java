package com.lby.template.config.oauth;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.lby.template.enums.ResponseEnum;
import com.lby.template.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: laishao
 * Date: 2022/5/11
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        AuthExceptionHandler handler = new AuthExceptionHandler();
        resources
                .resourceId("resource")
                .stateless(true)
                .tokenServices(tokenServices)
                .accessDeniedHandler(handler)
                .authenticationEntryPoint(handler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(new ResourceServerConfig.expiredSessionStrategy())
                .sessionRegistry(sessionRegistry);
        http
                .cors()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }

    private static class expiredSessionStrategy implements SessionInformationExpiredStrategy{
        @Override
        public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException {
            HttpServletResponse response = sessionInformationExpiredEvent.getResponse();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new Gson().toJson(ResponseVO.error(ResponseEnum.LOGGED_IN)));
        }
    }

    @Slf4j
    private static class AuthExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler{

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

            Throwable cause = authException.getCause();
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            if (cause instanceof InvalidTokenException) {
                log.error("InvalidTokenException : {}",cause.getMessage());
                //Token无效
                response.getWriter().write(new Gson().toJson(ResponseVO.error(ResponseEnum.ACCESS_TOKEN_INVALID)));
            } else {
                log.error("AuthenticationException : NoAuthentication");
                //资源未授权
                response.getWriter().write(new Gson().toJson(ResponseVO.error(ResponseEnum.UNAUTHORIZED)));
            }

        }

        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            //访问资源的用户权限不足
            log.error("AccessDeniedException : {}",accessDeniedException.getMessage());
            response.getWriter().write(JSON.toJSONString(new Gson().toJson(ResponseVO.error(ResponseEnum.ACCESS_TOKEN_INVALID))));
        }
    }


}
