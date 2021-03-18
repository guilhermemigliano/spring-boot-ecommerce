package br.gov.sp.fatec.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ped_id")
    private Long id;

    @Column(name = "ped_valor")
    private Float valor;

    
    @Column(name = "cli_id")
    private Long clienteId;

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Float getValor(){
        return this.valor;
    }

    public void setValor(Float valor){
        this.valor = valor;
    }

    public Long getClienteId(){
        return this.clienteId;
    }

    public void setClienteId(Long clienteId){
        this.clienteId = clienteId;
    }
    
}
