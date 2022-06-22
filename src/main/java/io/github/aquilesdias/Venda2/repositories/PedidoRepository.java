package io.github.aquilesdias.Venda2.repositories;

import io.github.aquilesdias.Venda2.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface PedidoRepository extends JpaRepository<Pedido, Id> {
}
