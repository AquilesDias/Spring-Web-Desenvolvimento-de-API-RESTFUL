package io.github.aquilesdias.Venda2.services.impl;

import io.github.aquilesdias.Venda2.domain.Cliente;
import io.github.aquilesdias.Venda2.domain.ItemPedido;
import io.github.aquilesdias.Venda2.domain.Pedido;
import io.github.aquilesdias.Venda2.domain.Produto;
import io.github.aquilesdias.Venda2.exception.RegraDeNegocioException;
import io.github.aquilesdias.Venda2.repositories.ClienteRepository;
import io.github.aquilesdias.Venda2.repositories.ItemPedidoRepository;
import io.github.aquilesdias.Venda2.repositories.PedidoRepository;
import io.github.aquilesdias.Venda2.repositories.ProdutoRepository;
import io.github.aquilesdias.Venda2.rest.dto.ItemPedidoDTO;
import io.github.aquilesdias.Venda2.rest.dto.PedidoDTO;
import io.github.aquilesdias.Venda2.services.PedidoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    private final ClienteRepository clienteRepository;

    private final ProdutoRepository produtoRepository;

    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO) {
        Integer idCliente = pedidoDTO.getCliente();

        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraDeNegocioException("Código id invalido!"));

        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemPedidos = converterItems(pedido, pedidoDTO.getItems());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itemPedidos);
        pedido.setItens(itemPedidos);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
         return pedidoRepository.findByIdFetchItens(id);
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){

        if(items.isEmpty()){ // verificando se a lista pedido está vazia
            throw new RegraDeNegocioException("Não é possivel realizar um pedido sem items");
        }

        return items
                .stream()
                .map( dto -> {
                            Integer idProduto = dto.getProduto();
                            Produto produto = produtoRepository
                                    .findById(idProduto)
                                    .orElseThrow(() -> new RegraDeNegocioException("Código de produto inválido"));

                            ItemPedido itemPedido = new ItemPedido();
                            itemPedido.setQuantidade(dto.getQuantidade());
                            itemPedido.setPedido(pedido);
                            itemPedido.setProduto(produto);
                            return itemPedido;
                       })
                .collect(Collectors.toList());
    }

}
