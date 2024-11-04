package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FastApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(FastApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FastApplication.class);
	}
//	@Bean
//	public CommonsMultipartResolver multipartResolver() {
//	    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//	    resolver.setMaxUploadSizePerFile(10 * 1024 * 1024); // Set your maximum file size
//	    return resolver;
//	}
	 @Bean
	 public WebMvcConfigurer corsConfiguer() 
	 {
		 return new WebMvcConfigurer() {
			 
			 @Override
			 public void addCorsMappings(CorsRegistry registry) {
				 registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
				 .allowedHeaders("*");
			 }
			 };
		
		 }
}
