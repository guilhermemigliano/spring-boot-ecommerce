package br.gov.sp.fatec.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping
    public List<Cliente> buscarClientes(){
        return segurancaService.buscarClientes();
    }

    @GetMapping(value = "/{id}")
    public Cliente buscarClientePorId(@PathVariable("id") Long id){
        return segurancaService.buscarClientePorId(id);
    }

    
}
