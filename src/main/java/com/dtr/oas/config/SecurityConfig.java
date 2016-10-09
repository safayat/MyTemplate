package com.dtr.oas.config;

import com.dtr.oas.enums.Roles;
import com.dtr.oas.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/super/**")
				.access("hasRole('" + Roles.ROLE_SUPER + "')")
				.antMatchers("/admin/**")
				.access("hasRole('" + Roles.ROLE_ADMIN + "')")
				.antMatchers("/public/**")
				.permitAll()
				.antMatchers("/private/**")
				.authenticated()
				.and()
				.formLogin()
				.loginProcessingUrl("/j_spring_security_check")
				.loginPage("/login").defaultSuccessUrl("/private/api/user").failureUrl("/public/api/login/error")
				.usernameParameter("username")
				.passwordParameter("password")
				.and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login?logout")
				.and().csrf().disable()
				.exceptionHandling()
				.authenticationEntryPoint(new AuthenticationEntryPoint() {
					@Override
					public void commence(HttpServletRequest request, HttpServletResponse response,
										 AuthenticationException authException) throws IOException, ServletException {
						if (authException != null) {
							response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
							response.getWriter().print("USER_IS_UN_AUTHORIZED");
						}
					}
				})
				.accessDeniedPage("/public/accessDenied");
	}



	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}



}