package com.eun.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
public class SecurityConfig {

    @Bean
    @Order(0)
    public SecurityFilterChain resources(HttpSecurity http) throws Exception {
        log.info(">>>>>>>>>>>>>>>>>>>>> resources!!");
        return http.requestMatchers(matchers -> matchers
                        .antMatchers("/js/member/**", "/js/common/**", "/css/**", "/favicon.ico"))
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll())
                .requestCache(RequestCacheConfigurer::disable)
                .securityContext(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info(">>>>>>>>>>>>>>>>>>>>> securityFilterChain!!");
//        return http.csrf(AbstractHttpConfigurer::disable)
//                .headers(headers -> headers
//                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
//                .authorizeRequests(authorize -> authorize
//                        .antMatchers("/member").hasRole("USER")
//                        .anyRequest().authenticated())
//                .formLogin(form -> form
//                        .usernameParameter("email")
//                        .usernameParameter("password")
//                        .loginPage("/member/login").permitAll()
//                        .defaultSuccessUrl("/"))
//                .logout(logout -> logout
//                        .logoutUrl("/member/logout"))
//                .build();

        http.authorizeRequests().antMatchers("/member/login").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/member/login")
                .usernameParameter("email")
                .permitAll()
                .and()
                .logout().permitAll();

        http.headers().frameOptions().sameOrigin();

        return http.build();
    }

}