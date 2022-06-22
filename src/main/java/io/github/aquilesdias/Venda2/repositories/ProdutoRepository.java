package io.github.aquilesdias.Venda2.repositories;

import io.github.aquilesdias.Venda2.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
