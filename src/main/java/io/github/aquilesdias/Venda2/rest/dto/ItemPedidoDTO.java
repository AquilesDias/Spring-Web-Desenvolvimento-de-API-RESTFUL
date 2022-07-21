package io.github.aquilesdias.Venda2.rest.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {

    private Integer produto;
    private Integer quantidade;

}
