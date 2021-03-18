package br.gov.sp.fatec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.ecommerce.entity.Autorizacao;

public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long> {
    
}
