package br.com.api.f1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class F1ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(F1ApiApplication.class, args);
	}

}
