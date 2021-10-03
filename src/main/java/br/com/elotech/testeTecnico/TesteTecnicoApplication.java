package br.com.elotech.testeTecnico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("br.com.elotech.testeTecnico.domain")
public class
TesteTecnicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteTecnicoApplication.class, args);
	}

}
