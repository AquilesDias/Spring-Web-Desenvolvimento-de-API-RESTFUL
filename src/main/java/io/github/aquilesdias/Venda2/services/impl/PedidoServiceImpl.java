package io.github.aquilesdias.Venda2.services.impl;

import io.github.aquilesdias.Venda2.repositories.PedidoRepository;
import io.github.aquilesdias.Venda2.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

}
