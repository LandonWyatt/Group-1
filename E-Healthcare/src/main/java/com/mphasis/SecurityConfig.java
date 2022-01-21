package com.mphasis;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//this will enable the security for all the end points. 
	
	
	  @Override 
		protected void configure(final HttpSecurity http) throws Exception {
		  http
			.authorizeRequests()
				.antMatchers("/", "/index").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();

		}
	 
	
	@Override
	public void configure(WebSecurity web) {
	    web.ignoring()
	        .antMatchers("/resources/**")
	        .anyRequest()
	        ;
	}

}
