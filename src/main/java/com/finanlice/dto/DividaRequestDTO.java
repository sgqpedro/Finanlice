package com.finanlice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DividaRequestDTO {

    private String descricao;
    private BigDecimal valor;
    private BigDecimal valorPago;
    private String categoria;
    private LocalDate vencimento;
    private String status;
    private Long usuarioId;


    public DividaRequestDTO(){}

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public BigDecimal getValor(){
        return valor;
    }

    public void setValor(BigDecimal valor){
        this.valor = valor;

    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago){
        this.valorPago = valorPago;
    }

    public String getCategoria(){
        return categoria;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public LocalDate getVencimento(){
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento){
        this.vencimento = vencimento;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}

