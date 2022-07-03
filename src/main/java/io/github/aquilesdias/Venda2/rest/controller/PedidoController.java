package io.github.aquilesdias.Venda2.rest.controller;

import io.github.aquilesdias.Venda2.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;


}
