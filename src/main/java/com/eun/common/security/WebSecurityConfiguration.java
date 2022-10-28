package com.eun.common.security;

import com.eun.common.property.Endpoint;
import com.eun.common.security.services.LoginFailureHandler;
import com.eun.common.security.services.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

	@Autowired LoginSuccessHandler loginSuccessHandler;
	@Autowired LoginFailureHandler loginFailureHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.httpBasic().disable()
			.requestCache(RequestCacheConfigurer::disable)
			.exceptionHandling().accessDeniedPage("/404.html")
		;

//		http
//			.addFilterBefore(new AuthTokenFilter(), UsernamePasswordAuthenticationFilter.class)
//		;

		// form login 관련 설정
		http
			.formLogin()
			.loginPage(Endpoint.LOGIN)						// 인증이 필요한 URL에 접근하면 이동할 페이지
			.usernameParameter("email")						// 로그인 시 form에서 가져올 값(email)
			.passwordParameter("password")					// 로그인 시 form에서 가져올 값(pw)
			.loginProcessingUrl(Endpoint.LOGIN_PROCESS)		// 로그인을 처리할 URL 입력
			.successHandler(loginSuccessHandler)			// 로그인 성공시 실행
			.failureHandler(loginFailureHandler)			// 로그인 실패시 실행
		;

		// form logout 관련 설정
		http
			.logout()
			.logoutUrl(Endpoint.LOGOUT)
			.logoutSuccessUrl(Endpoint.LOGIN)
		;

		// session 설정
		http
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.NEVER)
			.sessionFixation().migrateSession()
			.maximumSessions(1)
			.expiredUrl(Endpoint.LOGIN)
		;

		http
			.authorizeRequests()
				.antMatchers("/js/member/**", "/js/common/**", "/css/**", "/favicon.ico")
					.permitAll()
				.mvcMatchers("/member/login", "/member/create")
					.permitAll()
				.mvcMatchers("/student/**", "/js/student/**", "/todo/**")
					.hasAnyAuthority("ROLE_STUDENT", "ROLE_PROFESSOR", "ROLE_ADMIN")
				.mvcMatchers("/professor/**", "/js/professor/**")
					.hasAnyAuthority("ROLE_PROFESSOR", "ROLE_ADMIN")
				.mvcMatchers("/admin/**", "/js/admin/**")
					.hasAnyAuthority("ROLE_ADMIN")
				.anyRequest().authenticated()
		;

		return http.build();
    }

}
