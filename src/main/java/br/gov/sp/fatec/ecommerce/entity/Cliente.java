package br.gov.sp.fatec.ecommerce.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cli_id")
    private Long id;

    @Column(name = "cli_nome")
    private String nome;

    @Column(name = "cli_email")
    private String email;

    @Column(name = "cli_senha")
    private String senha;

    //Fetch => EAGER vai fazer o join e preencher todas as autorizações // LAZY => Faz select quando precisar usar com o get 
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "uau_usuario_autorizacao",
        joinColumns = {@JoinColumn(name = "cli_id")},
        inverseJoinColumns = {@JoinColumn(name = "aut_id")}
        )
    private Set<Autorizacao> autorizacoes;

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

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getSenha(){
        return this.senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public Set<Autorizacao> getAutorizacoes(){
        return this.autorizacoes;
    }

    public void setAutorizacoes(Set<Autorizacao> autorizacoes){
        this.autorizacoes = autorizacoes;
    }
    
}
