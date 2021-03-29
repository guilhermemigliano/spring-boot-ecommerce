package br.gov.sp.fatec.ecommerce.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.ecommerce.entity.Autorizacao;
import br.gov.sp.fatec.ecommerce.entity.Cliente;
import br.gov.sp.fatec.ecommerce.entity.Pedido;
import br.gov.sp.fatec.ecommerce.repository.AutorizacaoRepository;
import br.gov.sp.fatec.ecommerce.repository.ClienteRepository;
import br.gov.sp.fatec.ecommerce.repository.PedidoRepository;

//como o component => onde vai ter a regra de negócio
@Service("segurancaService")
public class SegurancaServiceImpl implements SegurancaService {

    @Autowired
    private AutorizacaoRepository autRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private PedidoRepository pedidoRepo;

    @Transactional
    public Cliente criarCliente(String nome, String email, String senha, String autorizacao) {
        Autorizacao aut = autRepo.findByNome(autorizacao);
        if (aut == null) {
            aut = new Autorizacao();
            aut.setNome(autorizacao);
            autRepo.save(aut);
        }
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setSenha(senha);
        //HashSet não permite ter elementos iguais.
        cliente.setAutorizacoes(new HashSet<Autorizacao>());
        cliente.getAutorizacoes().add(aut);
        clienteRepo.save(cliente);
        return cliente;

    }

    
    @Transactional
    public Pedido criarPedido(String nome, double valor, String email) throws Exception {
        Cliente cliente = clienteRepo.buscarClientePorEmail(email);
        Pedido pedido = new Pedido();
        if(cliente != null){
            
            pedido.setNome(nome);
            pedido.setValor(valor);
            cliente.setPedidos(new HashSet<Pedido>());
            cliente.getPedidos().add(pedido);
            pedidoRepo.save(pedido);
            clienteRepo.save(cliente);   
            return pedido;      

        }        
       
        throw new Exception("Cliente não encontrado");
       
        
    }

    @Override
    public List<Cliente> buscarClientes(){
        return clienteRepo.findAll();
    }

    @Override
    public Cliente buscarClientePorId(Long id){
        Optional<Cliente> clienteOp = clienteRepo.findById(id);
        if(clienteOp.isPresent()) {
            return clienteOp.get();            
        }
        throw new RuntimeException("Cliente não encontrado!");
    }

    @Override
    public Cliente buscarClientePorNome(String nome){
        Cliente cliente = clienteRepo.findByNome(nome);
        if(cliente != null) {
            return cliente;            
        }
        throw new RuntimeException("Cliente não encontrado!");
    }

    @Override
    public Autorizacao buscarAutorizacaoPorNome(String nome){
        Autorizacao autorizacao = autRepo.findByNome(nome);
        if(autorizacao != null){
            return autorizacao;
        }
        throw new RuntimeException("Autorização não encontrada");
    }

    @Override
    public List<Pedido> listarPedidos(){
        return pedidoRepo.findAll();
    }

    @Override
    public Pedido buscarPedidoPorNome(String nome){
        Pedido pedido = pedidoRepo.findByNome(nome);
        if(pedido != null){
            return pedido;
        }
        throw new RuntimeException("Pedido não encontrado");
    }

    @Override
    public Pedido buscarPedidoPorId(Long id){
        Pedido pedido = pedidoRepo.buscaPedidoPorId(id);
        if(pedido != null) {
            return pedido;           
        }
        throw new RuntimeException("Pedido não encontrado!");

    }
    
}
