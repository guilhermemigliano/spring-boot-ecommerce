package br.gov.sp.fatec.ecommerce.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import br.gov.sp.fatec.ecommerce.entity.Autorizacao;
import br.gov.sp.fatec.ecommerce.entity.Cliente;
import br.gov.sp.fatec.ecommerce.entity.Pedido;
import br.gov.sp.fatec.ecommerce.exception.RegistroNaoEncontradoException;
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

    @Autowired
    private PasswordEncoder passEncoder;

    @Transactional
    //@PreAuthorize("isAuthenticated()")
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
        cliente.setSenha(passEncoder.encode(senha));
        // HashSet não permite ter elementos iguais.
        cliente.setAutorizacoes(new HashSet<Autorizacao>());
        cliente.getAutorizacoes().add(aut);
        clienteRepo.save(cliente);
        return cliente;

    }

    @Transactional
    @PreAuthorize("isAuthenticated()")
    public Pedido criarPedido(String nome, double valor, String email) {
        Cliente cliente = clienteRepo.buscarClientePorEmail(email);
        Pedido pedido = new Pedido();
        if (cliente != null) {

            pedido.setNome(nome);
            pedido.setValor(valor);
            cliente.setPedidos(new HashSet<Pedido>());
            cliente.getPedidos().add(pedido);
            pedido.setClientes(cliente);
            pedidoRepo.save(pedido);
            clienteRepo.save(cliente);
            return pedido;

        }
        throw new RegistroNaoEncontradoException("Cliente não encontrado!");

    }

    @PreAuthorize("isAuthenticated()")
    public Cliente atualizarCliente(String nome, String email, String senha, Long id) {
        Cliente cliente = clienteRepo.buscarClientePorId(id);
        if (cliente != null) {
            cliente.setNome(nome);
            cliente.setEmail(email);
            cliente.setSenha(senha);
            clienteRepo.save(cliente);

            return cliente;
        }

        throw new RegistroNaoEncontradoException("Cliente não encontrado!");
    }

    @PreAuthorize("isAuthenticated()")
    public Pedido atualizarValorPedido(double valor, Long id) {

        Pedido pedido = pedidoRepo.buscaPedidoPorId(id);

        if (pedido != null) {
            pedido.setValor(valor);
            pedidoRepo.save(pedido);
            return pedido;
        }

        throw new RegistroNaoEncontradoException("Pedido não encontrado!");

    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Cliente> buscarClientes() {
        return clienteRepo.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Autorizacao> listarAutorizacoes() {
        return autRepo.findAll();
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Cliente buscarClientePorId(Long id) {
        Optional<Cliente> clienteOp = clienteRepo.findById(id);
        if (clienteOp.isPresent()) {
            return clienteOp.get();
        }
        throw new RegistroNaoEncontradoException("Cliente não encontrado!");
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Cliente buscarClientePorNome(String nome) {
        Cliente cliente = clienteRepo.findByNome(nome);
        if (cliente != null) {
            return cliente;
        }
        throw new RegistroNaoEncontradoException("Cliente não encontrado!");
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Autorizacao buscarAutorizacaoPorNome(String nome) {
        Autorizacao autorizacao = autRepo.findByNome(nome);
        if (autorizacao != null) {
            return autorizacao;
        }
        throw new RegistroNaoEncontradoException("Autorização não encontrada");
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public List<Pedido> listarPedidos() {
        return pedidoRepo.findAll();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Pedido buscarPedidoPorNome(String nome) {
        Pedido pedido = pedidoRepo.findByNome(nome);
        if (pedido != null) {
            return pedido;
        }
        throw new RegistroNaoEncontradoException("Pedido não encontrado");
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Pedido buscarPedidoPorId(Long id) {
        Pedido pedido = pedidoRepo.buscaPedidoPorId(id);
        if (pedido != null) {
            return pedido;
        }
        throw new RegistroNaoEncontradoException("Pedido não encontrado!");

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Cliente cliente = clienteRepo.findByNome(username);
        if (cliente == null) {
            throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
        }
        return User.builder().username(username).password(cliente.getSenha())
                .authorities(cliente.getAutorizacoes().stream().map(Autorizacao::getNome).collect(Collectors.toList())
                        .toArray(new String[cliente.getAutorizacoes().size()]))
                .build();
    }
    //authorities é um vetor de string -> pega as aut transforma em uma lista, tira os nomes, e depois passar para o array


    @Override
    public String buscarAutorizacaoUsuario(String autorizacao) {
        Cliente cliente = clienteRepo.findByNome(autorizacao);
        return cliente.getAutorizacoes().iterator().next().getNome();
    }

}


