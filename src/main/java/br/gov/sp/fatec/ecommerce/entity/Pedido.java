package br.gov.sp.fatec.ecommerce.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.ecommerce.controller.View;


@Entity
@Table(name = "pedido")
public class Pedido {
    
    @JsonView(View.PedidoLista.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ped_id")
    private Long id;

    @JsonView({View.ClienteResumo.class, View.PedidoLista.class})
    @Column(name = "ped_nome")
    private String nome;

    @JsonView({View.ClienteResumo.class, View.PedidoLista.class})
    @Column(name = "ped_valor")
    private double valor; 
    
    @JsonView({View.PedidoLista.class})
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "pedidos")    
    private Set<Cliente> cliente;
    

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public double getValor(){
        return this.valor;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    public Set<Cliente> getClientes(){
        return this.cliente;
    }

    public void setClientes(Set<Cliente> clientes){
        this.cliente = clientes;
    }

    
    
}
