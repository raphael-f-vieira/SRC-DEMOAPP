package com.example.demo.controller.dto;

import com.example.demo.entity.Beneficiario;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class AtualizaBeneficiarioFormDTO {
    private String nome;
    private String telefone;
    private Date dataNascimento;

    public void atualiza(Beneficiario beneficiario) {
        Date now = new Date(System.currentTimeMillis());

        beneficiario.setNome(this.nome);
        beneficiario.setTelefone(this.telefone);
        beneficiario.setDataNascimento(this.getDataNascimento());
        beneficiario.setDataAtualizacao(now);
    }
}