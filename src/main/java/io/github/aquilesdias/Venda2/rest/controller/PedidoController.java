package io.github.aquilesdias.Venda2.rest.controller;

import io.github.aquilesdias.Venda2.domain.Pedido;
import io.github.aquilesdias.Venda2.rest.dto.PedidoDTO;
import io.github.aquilesdias.Venda2.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}
