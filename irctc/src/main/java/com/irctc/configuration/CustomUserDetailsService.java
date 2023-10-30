package com.irctc.configuration;

import com.irctc.exception.ApiException;
import com.irctc.user.entity.User;
import com.irctc.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	
	@Autowired
	private UserRepository userRepo;
	
	public String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("password for load user  " +this.password);
		User user = this.userRepo.findUserByEmail(username);
		if(user==null){
			throw new ApiException("user not found");
		}
		return user;
	}

}
