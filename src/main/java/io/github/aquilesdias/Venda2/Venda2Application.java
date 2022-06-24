package io.github.aquilesdias.Venda2;

import io.github.aquilesdias.Venda2.domain.Cliente;
import io.github.aquilesdias.Venda2.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Venda2Application {

	@Bean
	CommandLineRunner commandLineRunner (@Autowired ClienteRepository clienteRepository){
		return args -> {
			Cliente cliente = new Cliente(null, "Flamengo");
			clienteRepository.save(cliente);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Venda2Application.class, args);
	}

}
