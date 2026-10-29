package com.finanlice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DividaResponseDTO {

    private Long id;
    private String descricao;
    private BigDecimal valor;
    private BigDecimal valorPago;
    private String categoria;
    private LocalDate vencimento;
    private String status;
    private Long usuarioId;

    public DividaResponseDTO() {
    }

    public DividaResponseDTO(Long id, String descricao, BigDecimal valor,
                             BigDecimal valorPago, String categoria,
                             LocalDate vencimento, String status, Long usuarioId) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.valorPago = valorPago;
        this.categoria = categoria;
        this.vencimento = vencimento;
        this.status = status;
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
