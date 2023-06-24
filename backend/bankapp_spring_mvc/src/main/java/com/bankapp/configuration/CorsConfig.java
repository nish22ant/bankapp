package com.bankapp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Enabling CORS
 * @author nknis
 *
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer  {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")  // Specify the path pattern for which CORS should be applied
        .allowedOrigins("http://localhost:3000", "https://example.com")  // List of allowed origins
        .allowedMethods("GET", "POST", "PUT", "DELETE")  // List of allowed HTTP methods
        .allowedHeaders("Authorization", "Content-Type")  // List of allowed headers
        .allowCredentials(true)  // Allow sending cookies from the browser
        .maxAge(3600); 
	}

}
