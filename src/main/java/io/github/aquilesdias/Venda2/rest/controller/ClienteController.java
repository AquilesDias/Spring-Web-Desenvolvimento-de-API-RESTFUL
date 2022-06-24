package io.github.aquilesdias.Venda2.rest.controller;

import io.github.aquilesdias.Venda2.domain.Cliente;
import io.github.aquilesdias.Venda2.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/hello/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> findByClienteId(@PathVariable Integer id){
        Optional<Cliente> existId = clienteRepository.findById(id);
        if(existId.isPresent()){
            return ResponseEntity.ok( existId.get());
        }
        return ResponseEntity.notFound().build();
    }


}
