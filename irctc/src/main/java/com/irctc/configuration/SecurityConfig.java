package com.irctc.configuration;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
//@EnableWebSecurity
//@EnableWebMvc
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableMethodSecurity
public class SecurityConfig {


	
	@Autowired
	private CustomUserDetailsService customUserDetailsService; 
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	@Autowired
	private JwtAuthenticationFilter filter;
	@Autowired
	private JwtTokenHelperNew tokenHelper;
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//         httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/auth/**","/test/**","/register","/**").permitAll()
//                        .anyRequest().authenticated()
//                ).oauth2Login(Customizer.withDefaults())
//                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .httpBasic(Customizer.withDefaults())
//                ;

		httpSecurity .csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth
						.requestMatchers("/auth/**","/test/**","/register","/**").permitAll()
						.anyRequest().authenticated()
		).
				oauth2Login(oauth2Customize -> oauth2Customize
				.loginProcessingUrl("/login")
				.loginPage("https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?response_type=code&client_id=113122694590-b4fkob10cjnoj0bp9omionja07eb4ok1.apps.googleusercontent.com&scope=openid%20profile%20email&state=2yyqqm2AnIt7vu0yD4PBHQZINuKg5lOoWi5m_TrEqlE%3D&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Flogin%2Foauth2%2Fcode%2Fgoogle&nonce=D0m4beelriOGXyKz-9cWwyR_3qPwGlfMFEsXhSoZbGM&service=lso&o2v=2&theme=glif&flowName=GeneralOAuthFlow")
				.successHandler(new AuthenticationSuccessHandler() {
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
						request.authenticate(response);
					}
				}));

//				oauth2Login(Customizer.withDefaults());
		httpSecurity.addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception{
    	return configuration.getAuthenticationManager();
    }
	
	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.customUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

}
