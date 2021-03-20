package br.gov.sp.fatec.ecommerce;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.ecommerce.entity.Autorizacao;
import br.gov.sp.fatec.ecommerce.entity.Cliente;
import br.gov.sp.fatec.ecommerce.repository.AutorizacaoRepository;
import br.gov.sp.fatec.ecommerce.repository.ClienteRepository;
import br.gov.sp.fatec.ecommerce.service.SegurancaService;

@SpringBootTest
@Transactional //cada metodo da classe abre uma transação nova e abre uma conexão
@Rollback //terminou o metodo ele não da commit
class EcommerceApplicationTests {

    @Autowired //sprint identiciar que precisa encontrar algo do tipo ClienteRepository
    private ClienteRepository clienteRepo;

    @Autowired //sprint identiciar que precisa encontrar algo do tipo ClienteRepository
    private AutorizacaoRepository autRepo;

    @Autowired
    private SegurancaService segService;

	@Test
	void contextLoads() {
    }

    @Test
    void testaInsercao(){
        Cliente cliente = new Cliente();
        cliente.setNome("Usuario");
        cliente.setEmail("usuario@fatec.com.br");
        cliente.setSenha("senha12345");
        cliente.setAutorizacoes(new HashSet<Autorizacao>());
        Autorizacao aut = new Autorizacao();        
        aut.setNome("ROLE_USER1");
        autRepo.save(aut);
        cliente.getAutorizacoes().add(aut);
        clienteRepo.save(cliente);
        assertNotNull(cliente.getAutorizacoes().iterator().next().getId());
    }

    @Test
    void testaInsercaoAutorizacao(){
        Cliente cliente = new Cliente();
        cliente.setNome("Usuario2");
        cliente.setEmail("usuario2@fatec.com.br");
        cliente.setSenha("senha12345");
        clienteRepo.save(cliente);        
        Autorizacao aut = new Autorizacao();        
        aut.setNome("ROLE_USER2");
        aut.setClientes(new HashSet<Cliente>());
        aut.getClientes().add(cliente);
        autRepo.save(aut);      
        assertNotNull(aut.getClientes().iterator().next().getId());
    }

    @Test
    void testaAutorizacao(){
        Cliente cliente = clienteRepo.findById(1L).get();
        assertEquals("ROLE_ADMIN", cliente.getAutorizacoes().iterator().next().getNome());
       
    }

    @Test
    void testaUsuario(){
        Autorizacao aut = autRepo.findById(1L).get();
        assertEquals("Guilherme", aut.getClientes().iterator().next().getNome());
       
    }

    @Test
    void testaBuscaClienteNomeContains(){
        List<Cliente> clientes = clienteRepo.findByNomeContainsIgnoreCase("A");
        assertFalse(clientes.isEmpty());       
    }

    @Test
    void testaBuscaClienteNome(){
        Cliente cliente = clienteRepo.findByNome("Guilherme");
        assertNotNull(cliente);       
    }

    @Test
    void testaBuscaClienteNomeSenha(){
        Cliente cliente = clienteRepo.findByNomeAndSenha("Guilherme", "senha12345");
        assertNotNull(cliente);       
    }

    @Test
    void testaBuscaClienteNomeAutorizacao(){
        List<Cliente> clientes = clienteRepo.findByAutorizacoesNome("ROLE_ADMIN");
        assertFalse(clientes.isEmpty());       
    }

    @Test
    void testaBuscaClienteNomeQuery(){
        Cliente cliente = clienteRepo.buscaClientePorNome("Guilherme");
        assertNotNull(cliente);       
    }

    @Test
    void testaBuscaClienteNomeSenhaQuery(){
        Cliente cliente = clienteRepo.buscaUsuarioPorNomeESenha("Guilherme", "senha12345");
        assertNotNull(cliente);       
    }

    @Test
    void testaBuscaClienteNomeAutorizacaoQuery(){
        List<Cliente> clientes = clienteRepo.buscaPorNomeAutorizacao("ROLE_ADMIN");
        assertFalse(clientes.isEmpty());       
    }

    @Test
    void testaServicoCriaUsuario(){
        Cliente cliente = segService.criarCliente("Normal", "normal@normal.com.br", "senha12345", "ROLE_USER");
        assertNotNull(cliente);
    }

}
