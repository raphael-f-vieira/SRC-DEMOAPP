package com.example.demo.controller.dto;

import com.example.demo.entity.Beneficiario;
import lombok.Getter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BeneficiarioDTO {
    private Long id;
    private String nome;
    private String telefone;
    private Date dataNascimento;
    private Date dataInclusao;
    private Date dataAtualizacao;
    private List<DocumentoDTO> documentos = new ArrayList<>();

    public BeneficiarioDTO(Beneficiario beneficiario) {
        this.id = beneficiario.getId();
        this.nome = beneficiario.getNome();
        this.telefone = beneficiario.getTelefone();
        this.dataNascimento = beneficiario.getDataNascimento();
        this.dataInclusao = beneficiario.getDataInclusao();
        this.dataAtualizacao = beneficiario.getDataAtualizacao();

        for (int i=0; i < beneficiario.getDocumentos().size(); i++) {
            DocumentoDTO docDTO = new DocumentoDTO(beneficiario.getDocumentos().get(i));
            this.documentos.add(docDTO);
        }
    }

    public static List<BeneficiarioDTO> converter(List<Beneficiario> beneficiarios) {
        return beneficiarios.stream().map(BeneficiarioDTO::new).collect(Collectors.toList());
    }

}