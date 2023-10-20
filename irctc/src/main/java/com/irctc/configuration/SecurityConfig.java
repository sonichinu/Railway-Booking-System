package com.irctc.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
//@EnableWebMvc
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableMethodSecurity
public class SecurityConfig {

	
	public static final String[] PUBLIC_URLS = {"/auth/login" , "/registeruser" ,"/sendemail" , "/v3/api-docs" , "/v2/api-docs"
			, "/swagger-resources/**" , "/swagger-ui/**", "/webjars/**","/stocks/**"}; 
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService; 
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	@Autowired
	private JwtAuthenticationFilter filter;
	@Autowired
	private JwtTokenHelperNew tokenHelper;
	
	
//	@Bean
//	public JwtAuthenticationFilter filter() {
//		return new JwtAuthenticationFilter();
//	};
	
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.
//        csrf().disable()
//        .authorizeHttpRequests()
//        .anyRequest()
//        .authenticated()
//        .and()
//        .exceptionHandling().authenticationEntryPoint(this.entryPoint)
//        .and()
//        .sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class);
//
//         http.authenticationProvider(daoAuthenticationProvider());
//         DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
//         return defaultSecurityFilterChain;
//    }
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();


    }
    
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .exceptionHandling().authenticationEntryPoint(entryPoint)
//                .and()
//                .addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class)
//                .csrf().disable().cors().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers(PUBLIC_URLS).permitAll()
//                        .anyRequest().authenticated())
//        ;
//        return http.build();
//    }

////	Samrat's Security filter
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//		return httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll().anyRequest().authenticated()).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).httpBasic(Customizer.withDefaults()).build();
//	}


    
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
	
//	@Bean
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}
}
