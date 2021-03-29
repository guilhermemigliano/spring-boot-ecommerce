package br.gov.sp.fatec.ecommerce.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.gov.sp.fatec.ecommerce.entity.Pedido;
import br.gov.sp.fatec.ecommerce.service.SegurancaService;

//metodos que estiverem aqui podem ser rotas (serviços)
@RestController
//permite acesso externo de qualquer lugar se não colocar as origens. 
@RequestMapping(value = "/pedido")
@CrossOrigin
public class PedidoController {

    @Autowired
    private SegurancaService segurancaService;

    @JsonView(View.PedidoLista.class)
    @GetMapping
    public List<Pedido> buscarClientes(){
        return segurancaService.listarPedidos();
    }
   
    
}
