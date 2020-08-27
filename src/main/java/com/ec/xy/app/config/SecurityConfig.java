package com.ec.xy.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.xiuye.util.cls.XType;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
		http.formLogin()
		.and()
		.authorizeRequests()
		.anyRequest()
		.authenticated();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return XType.newInstance(BCryptPasswordEncoder::new);
	}
}



