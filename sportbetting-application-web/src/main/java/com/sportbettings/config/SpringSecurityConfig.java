package com.sportbettings.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@Import(MvcConfig.class)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/home").access("hasRole('USER')").and().formLogin()
//				.loginPage("/login").failureUrl("/login?error").usernameParameter("email")
//				.passwordParameter("password").and().logout().logoutSuccessUrl("/login?logout").and().csrf().and()
//				.exceptionHandling().accessDeniedPage("/403").and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.NEVER);
		http.authorizeRequests().antMatchers("/home").permitAll().and().formLogin().loginPage("/login")
				.failureUrl("/login?error").usernameParameter("email").passwordParameter("password").successHandler(new AuthenticationSuccessHandler() {
					
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						redirectStrategy.sendRedirect(request, response, "/home");
						
					}
				});
	}
}
