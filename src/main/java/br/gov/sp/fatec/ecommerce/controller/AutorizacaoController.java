package br.gov.sp.fatec.ecommerce.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.gov.sp.fatec.ecommerce.entity.Autorizacao;
import br.gov.sp.fatec.ecommerce.service.SegurancaService;

//metodos que estiverem aqui podem ser rotas (serviços)
@RestController
//permite acesso externo de qualquer lugar se não colocar as origens. 
@RequestMapping(value = "/autorizacao")
@CrossOrigin

public class AutorizacaoController {

    @Autowired
    private SegurancaService segurancaService;

    @JsonView(View.ListarAutorizacoes.class)
    @GetMapping
    public List<Autorizacao> buscarAutorizacoes(){
        return segurancaService.listarAutorizacoes();
    }

   
    @JsonView(View.AutorizacaoResumo.class)
    @GetMapping(value = "/nome/{autorizacao}")
    public Autorizacao buscarAutorizacaoPorNome(@PathVariable("autorizacao") String nome){
        return segurancaService.buscarAutorizacaoPorNome(nome);
    }

    
}
