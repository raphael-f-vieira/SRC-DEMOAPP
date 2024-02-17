package com.example.demo.controller.dto;

import com.example.demo.entity.Beneficiario;
import com.example.demo.entity.Documento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor
public class BeneficiarioFormDTO {
    private String nome;
    private String telefone;
    private Date dataNascimento;
    private List<DocumentoFormDTO> documentos;

    public Beneficiario converter() {
        Date now = new Date(System.currentTimeMillis());
        Beneficiario beneficiario = new Beneficiario();
        List<Documento> listDocs = new ArrayList<>();

        beneficiario.setNome(nome);
        beneficiario.setTelefone(telefone);
        beneficiario.setDataNascimento(dataNascimento);
        beneficiario.setDataInclusao(now);
        beneficiario.setDataAtualizacao(now);

        for (int i=0; i < documentos.size(); i++) {
            Documento documento = new Documento();
            documento.setTipoDocumento(documentos.get(i).getTipoDocumento());
            documento.setDescricao(documentos.get(i).getDescricao());
            documento.setDataInclusao(now);
            documento.setDataAtualizacao(now);
            documento.setBeneficiario(beneficiario);

            listDocs.add(documento);
        }

        beneficiario.setDocumentos(listDocs);
        return beneficiario;
    }

}