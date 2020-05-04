package lu.waterhackers.map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
//@EntityScan("com.database.demo.model")
//@ComponentScan({"com.database.demo.controller"})


public class DatabaseBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseBackendApplication.class, args);
    }

    @Value("${allowed.cors.origins:http://localhost:4200}")
    private String allowedOrigins;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins(allowedOrigins.split(","));
			}
		};
	}
}
