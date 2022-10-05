package com.eun.config;

import com.eun.property.Endpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain resources(HttpSecurity http) throws Exception {
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
        http.authorizeRequests()
                    .antMatchers(Endpoint.LOGIN, Endpoint.MEMBER_CREATE)
                    .permitAll()
                    .anyRequest().authenticated()
                .and()
//                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
//                .accessDeniedHandler(customAccessDeniedHandler)
//                .and()
//                    .sessionManagement()
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .csrf().disable()
                    .cors().disable()
                    .formLogin().disable()
                    .httpBasic().disable()
                .logout()
                    .logoutSuccessUrl("/")
//                .and()
//                .oauth2Login()
//                    .authorizationEndpoint()
//                    .authorizationRequestRepository(cookieBasedAuthorizationRequestRepository)
//                .and()
//                    .userInfoEndpoint()
//                    .userService()
//                .and()
//                    .successHandler(authenticationSuccessHandler)
//                    .failureHandler(authenticationFailureHandler)

                ;
        return http.build();
    }

}