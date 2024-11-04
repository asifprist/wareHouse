package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.exception.UserAuthenticationEntryPoint;
import com.example.filter.UserFCDFilter;
import com.example.services.UserInfoUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityFCDUserLogin {

	@Autowired
	private UserAuthenticationEntryPoint userAuthEntryPoint;

	@Autowired
	private UserFCDFilter authFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	// authentication
	public UserDetailsService userDetailsService() {

		return new UserInfoUserDetailsService();
	}


	// create new security configuration method

	@SuppressWarnings({ "removal", "deprecation" })
	@Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http
               .csrf(csrf -> csrf.disable())
               .authorizeHttpRequests(requests -> requests
                       .requestMatchers("/student/signup").permitAll()
                       .requestMatchers("/student/**","/call/**","/files/**","/kyc/**","/lead/**","/profileimage/**").permitAll())
               
               .sessionManagement(management -> management
                       .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .authenticationProvider(authenticationProvider())
               .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
               .build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
}
//	@Bean
//	public SecurityFilterChain web(HttpSecurity http) throws Exception {
//		return http
//			.authorizeHttpRequests((authorize) -> {
//				try {
//					authorize
//						.requestMatchers("/regFCD**", "/regFCD/authenticate")
//						.hasAuthority("/h**")
//						.anyRequest().authenticated()
//						 .and()
//					   .sessionManagement(management -> management
//					   .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//					   .authenticationProvider(authenticationProvider())
//					   .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}).build();
// 
//	}


//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests()
//                .requestMatchers("/regFCD/saveFCDReg", "/regFCD/authenticate").permitAll()
//                .and().authorizeHttpRequests()
//                .requestMatchers("/regFCD/**").permitAll()
//                //.authenticated()
//                .and()
//                .sessionManagement(management -> management
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider())
//                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).build();
//	}



	
	

//	@Bean
//	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//	    return http.getSharedObject(AuthenticationManagerBuilder.class)
//	            .build();
//	}

//	@Bean
//	public UserDetailsService userDetailsService(PasswordEncoder encode) {
//		
//		return new UserFCDservice();
//	}
//	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http.cors().and().csrf().disable().authorizeHttpRequests().requestMatchers("/regFCD/**").permitAll()
//				.and().authorizeHttpRequests().requestMatchers("/FCDuser/**").hasAnyAuthority("USER", "ADMIN").and()
//				.formLogin().and().build();
//	}

//	private Customizer<FormLoginConfigurer<HttpSecurity>> withDefaults() {
//		// TODO Auto-generated method stub
//		return null;
//	}

