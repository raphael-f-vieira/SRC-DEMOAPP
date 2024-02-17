package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "tb_documentos")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "dataInclusao")
    private Date dataInclusao;

    @Column(name = "dataAtualizacao")
    private Date dataAtualizacao;

    @ManyToOne
    @JoinColumn(name="beneficiario_id")
    private Beneficiario beneficiario;

    public Documento() {}

}