package br.com.gleiston.likefood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.gleiston.likefood.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class LikefoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(LikefoodApplication.class, args);
	}

}
