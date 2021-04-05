package br.gov.sp.fatec.ecommerce.service;

import java.util.List;

import br.gov.sp.fatec.ecommerce.entity.Autorizacao;
import br.gov.sp.fatec.ecommerce.entity.Cliente;
import br.gov.sp.fatec.ecommerce.entity.Pedido;

public interface SegurancaService {

    public Cliente criarCliente(String nome, String email, String senha, String autorizacao);

    public Pedido criarPedido(String nome, double valor, String email);

    public List<Cliente> buscarClientes();

    public Cliente buscarClientePorId(Long id);

    public Cliente buscarClientePorNome(String nome);



    // Autorizacao

    public Autorizacao buscarAutorizacaoPorNome(String nome);
    public List<Autorizacao>  listarAutorizacoes();


    // Pedido

    public List<Pedido> listarPedidos();

    public Pedido buscarPedidoPorNome(String nome);

    public Pedido buscarPedidoPorId(Long id);

    

        
    

    
    
}
