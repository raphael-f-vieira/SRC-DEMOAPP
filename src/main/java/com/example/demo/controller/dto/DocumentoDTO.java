package com.example.demo.controller.dto;

import com.example.demo.entity.Documento;
import lombok.Getter;

import java.sql.Date;

@Getter
public class DocumentoDTO {
    private Long id;
    private String tipoDocumento;
    private String descricao;
    private Date dataInclusao;
    private Date dataAtualizacao;

    public DocumentoDTO(Documento documento) {
        this.id = documento.getId();
        this.tipoDocumento = documento.getTipoDocumento();
        this.descricao = documento.getDescricao();
        this.dataInclusao = documento.getDataInclusao();
        this.dataAtualizacao = documento.getDataAtualizacao();
    }

}