package com.finanlice.model;


import jakarta.persistence.*;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "receitas")
public class receitaModel {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private BigDecimal valorReceita;
    private String tipo;
    private LocalTime dataRecebimento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;

    public ReceitaModel(){}

        public Long getId(){
            return id;
        }

        public void setId(Long Id){
            this.id = id;
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


}
