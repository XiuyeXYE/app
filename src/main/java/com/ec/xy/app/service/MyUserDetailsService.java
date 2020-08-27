package com.ec.xy.app.service;

import javax.annotation.Resource;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xiuye.sharp.X;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Resource
	private PasswordEncoder PE; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		X.lnS("user name :",username);
		
		User user = new User(username,PE.encode("123456"),AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		
		return user;
	}

}
