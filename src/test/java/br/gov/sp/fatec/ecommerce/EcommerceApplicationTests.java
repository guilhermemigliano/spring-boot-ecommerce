package br.gov.sp.fatec.ecommerce;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.ecommerce.entity.Autorizacao;
import br.gov.sp.fatec.ecommerce.entity.Cliente;
import br.gov.sp.fatec.ecommerce.repository.AutorizacaoRepository;
import br.gov.sp.fatec.ecommerce.repository.ClienteRepository;

@SpringBootTest
@Transactional //cada metodo da classe abre uma transação nova
@Rollback //terminou o metodo ele não da commit
class EcommerceApplicationTests {

    @Autowired //sprint identiciar que precisa encontrar algo do tipo ClienteRepository
    private ClienteRepository clienteRepo;

    @Autowired //sprint identiciar que precisa encontrar algo do tipo ClienteRepository
    private AutorizacaoRepository autRepo;

	@Test
	void contextLoads() {
    }

    @Test
    void testaInsercao(){
        Cliente cliente = new Cliente();
        cliente.setNome("Usuario");
        cliente.setEmail("usuario@fatec.com.br");
        cliente.setSenha("senha12345");
        clienteRepo.save(cliente);
        assertNotNull(cliente.getId());
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

}
