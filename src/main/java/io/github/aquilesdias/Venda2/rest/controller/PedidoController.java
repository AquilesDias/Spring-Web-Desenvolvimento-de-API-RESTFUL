package io.github.aquilesdias.Venda2.rest.controller;

import io.github.aquilesdias.Venda2.domain.ItemPedido;
import io.github.aquilesdias.Venda2.domain.Pedido;
import io.github.aquilesdias.Venda2.rest.dto.InformacaoItemPedidoDTO;
import io.github.aquilesdias.Venda2.rest.dto.InformacoesPedidoDTO;
import io.github.aquilesdias.Venda2.rest.dto.PedidoDTO;
import io.github.aquilesdias.Venda2.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO){
        Pedido pedido = pedidoService.salvar(pedidoDTO);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return pedidoService
                .obterPedidoCompleto(id)
                .map( p -> converter(p))
                .orElseThrow(() ->
                         new ResponseStatusException(NOT_FOUND, "Pedido não encontrado"));
    }

    private InformacoesPedidoDTO converter(Pedido pedido) {
           return InformacoesPedidoDTO.builder()
                    .codigo(pedido.getId())
                    .cpf(pedido.getCliente().getCpf())
                    .nomeCliente(pedido.getCliente().getNome())
                    .total(pedido.getTotal())
                    .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .items(converter(pedido.getItens()))
                    .build();
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){

        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream()
                .map( item -> InformacaoItemPedidoDTO
                        .builder()
                        .descricao(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPrecoUnitario())
                        .quantidade(item.getQuantidade())
                        .build()).collect(Collectors.toList());

    }

}
