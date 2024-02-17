package com.example.demo.controller;

import com.example.demo.controller.dto.*;
import com.example.demo.entity.Beneficiario;
import com.example.demo.entity.Documento;
import com.example.demo.repository.BeneficiarioRepository;
import com.example.demo.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Transactional
    @PostMapping
    public String saveDoc(@RequestBody DocumentoForm2DTO formDoc) {
        Beneficiario beneficiarioTemp = beneficiarioRepository.findById(formDoc.getBeneficiarioId()).get();

        Date now = new Date(System.currentTimeMillis());

        List<Documento> documentos = new ArrayList<>();
        Documento documento = new Documento();
        documento.setTipoDocumento(formDoc.getTipoDocumento());
        documento.setDescricao(formDoc.getDescricao());
        documento.setDataInclusao(now);
        documento.setDataAtualizacao(now);
        documento.setBeneficiario(beneficiarioTemp);
        documentos.add(documento);

        beneficiarioTemp.setDocumentos(documentos);

        beneficiarioRepository.save(beneficiarioTemp);

        return "Documento registrado com sucesso!!!";
    }

    @Transactional
    @PutMapping("/{id}")
    public DocumentoDTO atualizar(@PathVariable Long id, @RequestBody AtualizaDocumentoFormDTO form) {
        final Optional<Documento> optDocumento = documentoRepository.findById(id);

        if(optDocumento.isPresent()){
            Documento documento = optDocumento.get();
            form.atualiza(documento);
            documentoRepository.save(documento);
            return new DocumentoDTO(documento);
        }

        System.out.println("Documento não encontrado");
        return null;
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){

        final Optional<Documento> optDocumento = documentoRepository.findById(id);

        if (optDocumento.isPresent()) {
            documentoRepository.deleteById(id);
        }
    }

}