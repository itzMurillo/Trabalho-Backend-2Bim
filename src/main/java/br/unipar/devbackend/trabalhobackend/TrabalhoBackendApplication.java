package br.unipar.devbackend.trabalhobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TrabalhoBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(TrabalhoBackendApplication.class, args);
	}
}
