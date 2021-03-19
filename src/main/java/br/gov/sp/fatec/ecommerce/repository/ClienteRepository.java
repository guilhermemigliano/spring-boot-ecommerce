package br.gov.sp.fatec.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.ecommerce.entity.Cliente;

//<Cliente, Long> indica a entidade e o tipo da chave primária
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public List<Cliente> findByNomeContainsIgnoreCase(String nome );

    public Cliente findByNome(String nome);

    public Cliente findByNomeAndSenha(String nome, String senha);

    public List<Cliente> findByAutorizacoesNome(String autorizacao);
    
}
