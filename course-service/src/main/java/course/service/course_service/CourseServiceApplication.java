package course.service.course_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import course.service.course_service.client.UserClientFallback;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CourseServiceApplication {



	@Bean
    public UserClientFallback userClientFallback() {
        return new UserClientFallback();
    }

	
	public static void main(String[] args) {
		SpringApplication.run(CourseServiceApplication.class, args);
	}

}
