package br.gov.sp.fatec.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.ecommerce.entity.Cliente;

//<Cliente, Long> indica a entidade e o tipo da chave primária
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //query method
    public List<Cliente> findByNomeContainsIgnoreCase(String nome );

    public Cliente findByNome(String nome);
    
    public Cliente findByNomeAndSenha(String nome, String senha);

    public List<Cliente> findByAutorizacoesNome(String autorizacao);

    

    //JPQL
    @Query("select c from  Cliente c where c.nome = ?1 ")
    public Cliente buscaClientePorNome (String nome);

    @Query("select c from Cliente c where c.nome = ?1 and c.senha = ?2")
    public Cliente buscaUsuarioPorNomeESenha (String nome, String senha);

    @Query("select c from Cliente c inner join c.autorizacoes a where a.nome = ?1")
    public List<Cliente> buscaPorNomeAutorizacao(String autorizacao);

    @Query("select c from  Cliente c where c.email = ?1 ")
    public Cliente buscarClientePorEmail (String email);

     @Query("select c from  Cliente c where c.id = ?1 ")
    public Cliente findById (Long id);


    //JPQL com cliente e pedidos

    @Query("select c from Cliente c inner join c.pedidos p where p.nome = ?1")
    public Cliente buscaPorNomePedido(String pedido);

    

    

    
    
}
