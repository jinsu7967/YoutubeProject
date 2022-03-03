package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	LoginIdPwValidator loginIdPwValidator;
	
	//경로 접근 설정
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/static/**");
	}
	
	//권한 및 로그인 설정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/**").permitAll()
				.and()
					.formLogin()
						.loginPage("/login")
						.loginProcessingUrl("/login/loginCheck")
						.defaultSuccessUrl("/")
						.usernameParameter("user_email")
						.passwordParameter("user_pw")
				.and()
					.logout()
						.logoutSuccessUrl("/")
						.logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
						.invalidateHttpSession(true);
		http.cors().and();
		http.csrf().disable();
						
						
	}
	
	//실질적인 로그인 과정(loginIdPwValidator Class를 이용)
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(loginIdPwValidator);
	}
	
}
