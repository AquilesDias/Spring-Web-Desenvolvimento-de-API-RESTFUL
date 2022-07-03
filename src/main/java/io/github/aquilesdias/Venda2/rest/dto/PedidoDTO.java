package io.github.aquilesdias.Venda2.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO {

    private Integer cliente;
    private BigDecimal total;
    List<ItemPedidoDTO> items;

}
