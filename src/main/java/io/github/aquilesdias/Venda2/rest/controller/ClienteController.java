package io.github.aquilesdias.Venda2.rest.controller;

import io.github.aquilesdias.Venda2.domain.Cliente;
import io.github.aquilesdias.Venda2.repositories.ClienteRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;


    /*****REQUISIÇÕES GET *****/
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity findByClienteId(@PathVariable Integer id){
        Optional<Cliente> existId = clienteRepository.findById(id);
        if(existId.isPresent()){
            return ResponseEntity.ok( existId.get());
        }
        return ResponseEntity.notFound().build();
    }

    /*****REQUISIÇÕES POST *****/
    @PostMapping
    @ResponseBody
    public ResponseEntity saveCliente( @RequestBody Cliente cliente){
        Cliente cliente1 = clienteRepository.save(cliente);
        return ResponseEntity.ok(cliente1);
    }






}
