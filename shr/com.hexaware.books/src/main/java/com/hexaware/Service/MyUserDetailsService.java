package com.hexaware.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.Entity.UserPrincipal;
import com.hexaware.Entity.Users;
import com.hexaware.Repository.UserRepo;

import org.springframework.security.core.userdetails.UserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    System.out.println("Loading user with username: " + username);
	    
	    Users user1 = repo.findByUsername(username);
	    
	    if (user1 == null) {
	        System.out.println("User not found");
	        throw new UsernameNotFoundException("User not found");
	    }
	   
	    return new UserPrincipal(user1);
	}



}