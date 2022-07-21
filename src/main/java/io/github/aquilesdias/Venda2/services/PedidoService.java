package io.github.aquilesdias.Venda2.services;

import io.github.aquilesdias.Venda2.domain.Pedido;
import io.github.aquilesdias.Venda2.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO pedidoDTO);

}
