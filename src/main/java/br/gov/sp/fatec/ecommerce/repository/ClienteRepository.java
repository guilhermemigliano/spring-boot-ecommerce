package br.gov.sp.fatec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.ecommerce.entity.Cliente;

//<Cliente, Long> indica a entidade e o tipo da chave primária
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
