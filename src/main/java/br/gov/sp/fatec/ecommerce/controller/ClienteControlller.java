package br.gov.sp.fatec.ecommerce.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.sp.fatec.ecommerce.entity.Cliente;
import br.gov.sp.fatec.ecommerce.service.SegurancaService;

//metodos que estiverem aqui podem ser rotas (serviços)
@RestController
//permite acesso externo de qualquer lugar se não colocar as origens. 
@RequestMapping(value = "/cliente")
@CrossOrigin
public class ClienteControlller {
    
    @Autowired
    private SegurancaService segurancaService;

    @JsonView(View.ClienteResumo.class)
    @GetMapping
    public List<Cliente> buscarClientes(){
        return segurancaService.buscarClientes();
    }

    @JsonView(View.ClienteCompleto.class)
    @GetMapping(value = "/id/{id}")
    public Cliente buscarClientePorId(@PathVariable("id") Long id){
        return segurancaService.buscarClientePorId(id);
    }

    @JsonView(View.ClienteResumo.class)
    @GetMapping(value = "/nome")
    public Cliente buscarClientePorNome(@RequestParam(value = "nome") String nome){
        return segurancaService.buscarClientePorNome(nome);
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrarNovoCliente(@RequestBody Cliente cliente,
        UriComponentsBuilder uriComponentsBuilder){
            cliente = segurancaService.criarCliente(cliente.getNome(), cliente.getEmail(), cliente.getSenha(), "ROLE_USER");
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(
                uriComponentsBuilder.path("/cliente/id/" + cliente.getId()).build().toUri());
            
                
        return new ResponseEntity<Cliente>(cliente, responseHeaders, HttpStatus.CREATED);
    }

    

    
}
