package com.irctc.configuration;

import com.irctc.exception.ApiException;
import com.irctc.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


import jakarta.websocket.Session;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private JwtTokenHelperNew helper;
	@Autowired
	private CustomUserDetailsService service;
	@Autowired
	private AuthenticationManager manager;

	@GetMapping("/get-current-user")
	public User user(Principal principal){
		return (User)(this.service.loadUserByUsername(principal.getName()));
	}

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request)
	{
//		UserDetails userDetails = this.service.loadUserByUsername(request.getUsername());
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.service.loadUserByUsername(request.getUsername());
		String generateToken = this.helper.generateToken(userDetails);
		System.out.println("*******************************" +generateToken);
		JwtAuthResponse authResponse = new JwtAuthResponse();
		authResponse.setAccessToken(generateToken);
		authResponse.setMessage("User logged in");

		return new ResponseEntity<JwtAuthResponse>(authResponse, HttpStatus.OK);

	}

	private void authenticate(String username, String password) {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(username, password);
		try {
			this.manager.authenticate(authtoken);
		}catch(Exception ex)
		{
			System.out.println("invalid details..!");
			throw new ApiException("Invalid Crediantials");
		}
	}
	
}
