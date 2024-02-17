package com.example.demo.controller.dto;

import com.example.demo.entity.Documento;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class AtualizaDocumentoFormDTO {
    private String tipoDocumento;
    private String descricao;

    public void atualiza(Documento documento) {
        Date now = new Date(System.currentTimeMillis());

        documento.setTipoDocumento(this.tipoDocumento);
        documento.setDescricao(this.descricao);
        documento.setDataAtualizacao(now);
    }
}