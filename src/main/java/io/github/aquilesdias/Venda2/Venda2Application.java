package io.github.aquilesdias.Venda2;

import io.github.aquilesdias.Venda2.domain.Cliente;
import io.github.aquilesdias.Venda2.domain.Pedido;
import io.github.aquilesdias.Venda2.repositories.ClienteRepository;
import io.github.aquilesdias.Venda2.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;





@SpringBootApplication
public class Venda2Application {

	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository repository, @Autowired PedidoRepository pedidoRepository){
		return args -> {
			System.out.println("salvando cliente");
			Cliente fulano = new Cliente("Lucas");
			repository.save(fulano);


			Pedido p = new Pedido();
			p.setCliente(fulano);
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));
			pedidoRepository.save(p);

			Cliente cliente1 = repository.findClienteFetchPedidos(fulano.getId());

		};

	}

	public static void main(String[] args) {
		SpringApplication.run(Venda2Application.class, args);
	}

}
