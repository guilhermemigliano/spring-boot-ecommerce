package br.gov.sp.fatec.ecommerce.service;

import br.gov.sp.fatec.ecommerce.entity.Cliente;

public interface SegurancaService {

    public Cliente criarCliente(String nome, String email, String senha, String autorizacao);
    
}
