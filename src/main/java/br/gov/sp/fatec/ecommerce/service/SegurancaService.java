package br.gov.sp.fatec.ecommerce.service;

import java.util.List;

import br.gov.sp.fatec.ecommerce.entity.Cliente;
import br.gov.sp.fatec.ecommerce.entity.Pedido;

public interface SegurancaService {

    public Cliente criarCliente(String nome, String email, String senha, String autorizacao);

    public Pedido criarPedido(String nome, double valor, String email) throws Exception;

    public List<Cliente> buscarClientes();

    public Cliente buscarClientePorId(Long id);
    
}
