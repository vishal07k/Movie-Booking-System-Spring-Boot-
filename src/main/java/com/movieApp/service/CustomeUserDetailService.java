//package com.movieApp.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class CustomeUserDetailService implements UserDetailsService{
//
//	@Autowired
//	private UserService service;
//	
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		
//	 com.movieApp.entities.User userLogin  = 	service.getUserforLogin(email);
//	 
//	 if(userLogin == null) {
//		 return null;
//	 }
//	 
//	 	 
//		return User.builder()
//				.username(userLogin.getEmailId())
//				.password(userLogin.getPassword())
//				.roles(userLogin.getRoles())
//				.build();
//	}
//
//	
//
//}
