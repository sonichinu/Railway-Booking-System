package com.irctc.configuration;

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
	


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = this.userRepo.findUserByEmail(username);
		if(user==null){
			System.out.println("user is null");
		}
		return user;
	}

}
