package com.pauljean.webconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("pauljean").password("").roles("USER").and().withUser("admin")
				.password("admin").roles("ADMIN");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().and().authorizeRequests().antMatchers("/", "/index").permitAll().and()
				.authorizeRequests().anyRequest().authenticated().anyRequest().hasAnyRole("USER", "ADMIN");

		http.formLogin().loginPage("/login").loginProcessingUrl("/home").usernameParameter("login")
				.passwordParameter("password").successHandler((req, res, auth) -> {
					for (GrantedAuthority authority : auth.getAuthorities()) {
						LOGGER.info(authority.getAuthority());
					}
					LOGGER.info(auth.getName());
					res.sendRedirect("./home");
				}).failureHandler((req, res, exp) -> {
					String errMsg = "";
					if (exp.getClass().isAssignableFrom(BadCredentialsException.class)) {
						errMsg = "Invalid username or password.";
					} else {
						errMsg = "error - " + exp.getMessage();
						LOGGER.info(errMsg);
					}
					req.getSession().setAttribute("message", errMsg);
					res.sendRedirect("./login");
				}).permitAll().and().logout().logoutUrl("/signout").logoutSuccessHandler((req, res, auth) -> {
					req.getSession().setAttribute("message", "logout sucess !!!");
					res.sendRedirect("/index"); 
				}).permitAll().and().csrf().disable();

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().regexMatchers("/resources/.*");
		web.ignoring().antMatchers("/*.css");
		web.ignoring().antMatchers("/*.js");
	}
}
