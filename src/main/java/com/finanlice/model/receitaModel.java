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

}
