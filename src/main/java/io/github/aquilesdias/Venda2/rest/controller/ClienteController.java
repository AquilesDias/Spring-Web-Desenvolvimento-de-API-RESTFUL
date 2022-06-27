package io.github.aquilesdias.Venda2.rest.controller;

import io.github.aquilesdias.Venda2.domain.Cliente;
import io.github.aquilesdias.Venda2.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;


    /***** REQUISIÇÕES GET *****/
    @GetMapping("/{id}")
    public Cliente findByClienteId(@PathVariable Integer id){
       return clienteRepository
               .findById(id)
               .orElseThrow(() ->
               new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não existe com esse Id!"));
    }

    @GetMapping
    public List<Cliente> find(Cliente clienteFiltro){

        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(clienteFiltro, exampleMatcher);

        return clienteRepository.findAll(example);
    }

    /***** REQUISIÇÕES POST *****/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente saveCliente( @RequestBody Cliente cliente){
       return clienteRepository.save(cliente);
    }

    /***** REQUISIÇÕES DELETE *****/
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Cliente deleteCliente(@PathVariable Integer id){
       return clienteRepository
               .findById(id)
               .map(cliente -> { clienteRepository.delete(cliente);
                   return cliente;
               })
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    /***** REQUISIÇÕES PUT *****/
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Cliente updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {

        return clienteRepository
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(cliente.getId());
                    clienteRepository.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }

}
