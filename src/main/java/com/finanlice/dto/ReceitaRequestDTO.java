package com.finanlice.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// DTO (Data Transfer Object) que recebe os dados enviados para cadastrar uma receita.
public class ReceitaRequestDTO {


    @NotBlank(message = "Nome da receita é obrigatório")
    @Size(max = 100, message = "O campo aceita no máximo 100 caracteres")
    private String nome;

    @NotNull(message = "O valor da Receita deve ser obrigatório")
    @DecimalMin( value = "0.00", message = "o valor não deve ser negativo")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal valorReceita;

    @NotBlank(message = "O Tipo da receita é obrigatório")
    @Size(max = 70, message = "O campo aceita no máximo 70 caracteres")
    private String tipo;

    @NotNull(message = "A data de recebimento é obrigatória")
    private LocalDateTime dataRecebimento;

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    // Construtor vazio, útil para frameworks que criam o DTO ao receber uma requisição.
    public ReceitaRequestDTO(){
    }

    // Construtor para criar o DTO já preenchido com os dados da receita.
    public ReceitaRequestDTO
            (String nome, BigDecimal valorReceita, String tipo , LocalDateTime dataRecebimento, Long usuarioId){
            this.nome = nome;
            this.valorReceita = valorReceita;
            this.tipo = tipo;
            this.dataRecebimento = dataRecebimento;
            this.usuarioId = usuarioId;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public BigDecimal getValorReceita(){
        return valorReceita;
    }

    public void setValorReceita(BigDecimal valorReceita){
        this.valorReceita = valorReceita;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public LocalDateTime getDataRecebimento(){
        return dataRecebimento;
    }

    public void setDataRecebimento(LocalDateTime dataRecebimento){
        this.dataRecebimento = dataRecebimento;
    }

    public Long getUsuarioId(){
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId){
        this.usuarioId = usuarioId;
    }

}
