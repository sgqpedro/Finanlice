package com.finanlice.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

// DTO (Data Transfer Object) que recebe os dados enviados para cadastrar uma dívida.
// As validações são verificadas quando a aplicação valida a requisição.
public class DividaRequestDTO {

    // Exige um texto não vazio e limita a descrição a 100 caracteres.
    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 100, message = "Descrição deve ter no máximo 100 caracteres")
    private String descricao;

    // Exige que o valor seja informado e que tenha pelo menos 0,01.
    // @Digits limita a 10 dígitos inteiros e 2 casas decimais.
    @NotNull(message = "Valor deve ser preenchido!")
    @DecimalMin(value = "0.01", message = "o valor não pode ser negativo")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal valor;

    // Valor já pago; pode ficar sem preenchimento, mas se informado deve ser pelo menos 0,01.
    // Também permite até 10 dígitos inteiros e 2 casas decimais.
    @DecimalMin(value = "0.01", message = "O valor pago não pode ser negativo")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal valorPago;

    // Exige que a categoria não seja nula nem vazia.
    @NotBlank(message = "Categoria é obrigatória")
    private String categoria;

    // Exige uma data de vencimento.
    @NotNull(message = "Vencimento é obrigatório")
    private LocalDate vencimento;

    // Identificador do usuário ao qual a dívida será associada.
    // A validação exige que esse identificador seja informado.
    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    // Construtor vazio usado pelo framework para criar o DTO ao receber a requisição.
    public DividaRequestDTO(){}


    // Getters e Setters.
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

