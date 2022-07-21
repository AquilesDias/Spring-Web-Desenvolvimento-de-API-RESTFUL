package io.github.aquilesdias.Venda2.rest.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Integer cliente;
    private BigDecimal total;
    List<ItemPedidoDTO> items;

}
