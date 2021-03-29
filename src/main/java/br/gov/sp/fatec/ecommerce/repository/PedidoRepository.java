package br.gov.sp.fatec.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.ecommerce.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("select p from Pedido p where p.id = ?1")
    public Pedido buscaPedidoPorId(Long id);

    @Query("select p from Pedido p where p.nome = ?1 and p.id = ?2")
    public Pedido buscaPedidoPorNomeEId(String nome, Long id);

    public Pedido findByNome(String pedido);

    public Optional<Pedido> findById(Long id);
    
}
