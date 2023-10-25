package com.irctc.configuration;

import com.irctc.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import jakarta.websocket.Session;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	private JwtTokenHelperNew helper;
	@Autowired
	private CustomUserDetailsService service;
	@Autowired
	private AuthenticationManager manager;
	public String password;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request)
	{
		this.password = request.getPassword();
		System.out.println("the password in auth controller is " +password);
//		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.service.loadUserByUsername(request.getUsername());
		this.authenticate(request.getUsername(), request.getPassword());
		System.out.println(userDetails);
		System.out.println("-------------------------------------------");
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
