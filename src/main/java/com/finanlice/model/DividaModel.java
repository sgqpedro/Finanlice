package com.finanlice.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "dividas")

public class DividaModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String descricao;

    private BigDecimal valor;

    private LocalDate vencimento;

    private String status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;

    public DividaModel(){}

    public DividaModel(Long id, String descricao, BigDecimal valor, LocalDate vencimento, String status){
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.vencimento = vencimento;
        this.status = status;
    }

    public Long getId(){ return id;}

    public void setId(Long id){this.id = id; }

    public String getDescricao(){ return descricao;}

    public void setDescricao(String descricao){this.descricao = descricao;}

    public BigDecimal getValor(){return valor;}

    public void setValor(BigDecimal valor){ this.valor = valor;}

    public LocalDate getVencimento(){return vencimento;}

    public void setVencimento(LocalDate vencimento){this.vencimento = vencimento;}

    public String getStatus(){ return status;}

    public void setStatus(String status){this.status = status;}

    public UsuarioModel getUsuario(){ return usuario;}

    public void setUsuario(UsuarioModel usuario) { this.usuario = usuario;}



}
