package io.github.aquilesdias.Venda2.repositories;

import io.github.aquilesdias.Venda2.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
