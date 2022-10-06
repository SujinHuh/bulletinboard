package com.eun.common.security;

import com.eun.common.property.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private CorsConfig corsConfig;

	@Bean
	public SecurityFilterChain resources(HttpSecurity http) throws Exception {
		return http.requestMatchers(matchers -> matchers
						.antMatchers("/js/member/**", "/js/common/**", "/css/**", "/favicon.ico"))
				.authorizeHttpRequests(authorize -> authorize
						.anyRequest().authenticated())
				.requestCache(RequestCacheConfigurer::disable)
				.securityContext(AbstractHttpConfigurer::disable)
				.sessionManagement(AbstractHttpConfigurer::disable)
				.build();
	}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.formLogin().disable()
			.httpBasic().disable()
			.addFilter(corsConfig.corsFilter())
			.requestCache(RequestCacheConfigurer::disable)
			.securityContext(AbstractHttpConfigurer::disable)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
				    .antMatchers("/js/member/**", "/js/common/**", "/css/**", "/favicon.ico")
						.permitAll()
					.mvcMatchers(Endpoint.LOGIN, Endpoint.MEMBER_CREATE)
						.permitAll()
					.mvcMatchers(Endpoint.KAKAO_PARSING)
						.hasAnyAuthority("ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN")
					.mvcMatchers(Endpoint.KAKAO_PARSING)
						.hasAnyAuthority("ROLE_MANAGER", "ROLE_ADMIN")
					.mvcMatchers(Endpoint.KAKAO_PARSING)
						.hasAnyAuthority("ROLE_ADMIN")
					.anyRequest().authenticated()
		;
		return http.build();
    }

}
