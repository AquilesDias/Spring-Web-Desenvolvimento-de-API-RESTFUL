package io.github.aquilesdias.Venda2.services;

import io.github.aquilesdias.Venda2.domain.Pedido;
import io.github.aquilesdias.Venda2.domain.enums.StatusPedido;
import io.github.aquilesdias.Venda2.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO pedidoDTO);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizarStatus(Integer id, StatusPedido statusPedido);

}
