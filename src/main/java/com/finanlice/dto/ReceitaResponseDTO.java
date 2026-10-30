package com.finanlice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReceitaResponseDTO {

    private Long id;
    private String nome;
    private BigDecimal valorReceita;
    private String tipo;
    private LocalDateTime dataRecebimento;
    private Long usuarioId;

    public ReceitaResponseDTO() {
    }

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
