package com.finanlice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// DTO (Data Transfer Object) que organiza os dados da receita enviados na resposta da API.
public class ReceitaResponseDTO {

    private Long id;
    private String nome;
    private BigDecimal valorReceita;
    private String tipo;
    private LocalDateTime dataRecebimento;
    private Long usuarioId;

    // Construtor vazio disponível para frameworks que precisam criar o DTO sem argumentos.
    public ReceitaResponseDTO() {
    }

    // Construtor para montar a resposta com todos os dados da receita.
    public ReceitaResponseDTO(Long id, String nome, BigDecimal valorReceita,
                              String tipo, LocalDateTime dataRecebimento, Long usuarioId) {
        this.id = id;
        this.nome = nome;
        this.valorReceita = valorReceita;
        this.tipo = tipo;
        this.dataRecebimento = dataRecebimento;
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorReceita() {
        return valorReceita;
    }

    public void setValorReceita(BigDecimal valorReceita) {
        this.valorReceita = valorReceita;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(LocalDateTime dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
