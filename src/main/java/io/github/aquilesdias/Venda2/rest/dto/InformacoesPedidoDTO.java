package io.github.aquilesdias.Venda2.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.aquilesdias.Venda2.domain.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO {

    private Integer codigo;
    private String cpf;
    private String dataPedido;
    private String nomeCliente;
    private String status;
    private BigDecimal total;
    private List<InformacaoItemPedidoDTO> items;


}
