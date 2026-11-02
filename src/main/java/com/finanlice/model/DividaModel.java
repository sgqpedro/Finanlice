package com.finanlice.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;


// @Entity informa ao JPA que esta classe deve ser salva e carregada do banco de dados.
@Entity
// @Table define o nome da tabela que armazenará os registros de dívida.
@Table(name = "dividas")

public class DividaModel{

    // @Id marca a chave primária; @GeneratedValue deixa o banco gerar seu valor.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private BigDecimal valorPago;
    private String categoria;
    private LocalDate vencimento;
    private String status;

    // @ManyToOne indica que várias dívidas podem pertencer ao mesmo usuário.
    @ManyToOne
    // @JoinColumn mapeia a relação para a coluna usuario_id nesta tabela.
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;

    // Construtor sem parâmetros usado pelo JPA ao carregar a entidade do banco.
    public DividaModel(){}

    // Construtor que permite criar uma dívida já informando seus dados principais.
    public DividaModel(Long id, String descricao, BigDecimal valor, BigDecimal valorPago,
                       String categoria, LocalDate vencimento, String status){
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.valorPago = valorPago;
        this.categoria = categoria;
        this.vencimento = vencimento;
        this.status = status;

    }


    // Getters e Setters.
    public Long getId(){ return id;}

    public void setId(Long id){
        this.id = id; }

    public String getDescricao(){
        return descricao;}

    public void setDescricao(String descricao){
        this.descricao = descricao;}

    public BigDecimal getValor(){
        return valor;}

    public void setValor(BigDecimal valor){
        this.valor = valor;}

    public BigDecimal getValorPago(){
        return valorPago;
    }

    public void setValorPago (BigDecimal valorPago){
        this.valorPago = valorPago;}

    public String getCategoria(){
        return categoria;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public LocalDate getVencimento(){
        return vencimento;}

    public void setVencimento(LocalDate vencimento){
        this.vencimento = vencimento;}

    public String getStatus(){
        return status;}

    public void setStatus(String status){
        this.status = status;}

    public UsuarioModel getUsuario(){
        return usuario;}

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;}



}
