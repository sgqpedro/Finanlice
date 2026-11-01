package com.finanlice.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DividaRequestDTO {


    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 100, message = "Descrição deve ter no máximo 100 caracteres")
    private String descricao;

    @NotNull(message = "Valor deve ser preenchido!")
    @DecimalMin(value = "0.01", message = "o valor não pode ser negativo")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal valor;

    @DecimalMin(value = "0.01", message = "O valor pago não pode ser negativo")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal valorPago;

    @NotBlank(message = "Categoria é obrigatória")
    private String categoria;

    @NotNull(message = "Vencimento é obrigatório")
    private LocalDate vencimento;



    @NotNull(message = "Usuário é obrigatório")
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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}

