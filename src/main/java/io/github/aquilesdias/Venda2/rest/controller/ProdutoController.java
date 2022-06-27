package io.github.aquilesdias.Venda2.rest.controller;

import io.github.aquilesdias.Venda2.domain.Produto;
import io.github.aquilesdias.Venda2.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    /***** REQUISIÇOES GET *****/

    @GetMapping("{id}")
    public Produto findByProdutoId(@PathVariable Integer id){
       return produtoRepository
                .findById(id)
                .orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto com id não encontrado")
                );
    }

    @GetMapping
    public List<Produto> findProdutos(Produto produtoFiltro){

        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(produtoFiltro, exampleMatcher);

        return produtoRepository.findAll(example);
    }

    /***** REQUISIÇOES POST *****/

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto produto(@RequestBody Produto produto){
        return produtoRepository.save(produto);
    }

    /***** REQUISIÇOES PUT *****/

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateProduto(@PathVariable Integer id, @RequestBody Produto produto){

         produtoRepository
                .findById(id)
                .map(produtoExistente -> {
                    produto.setId(produto.getId());
                    produtoRepository.save(produto);
                    return produtoExistente;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado")
                        );
    }

    /***** REQUISIÇOES DELETE *****/

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteProduto(@PathVariable Integer id){
         produtoRepository
                .findById(id)
                .map(produto -> { produtoRepository.delete(produto);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não foi deletado. Id não reconhecido."));
    }
}
