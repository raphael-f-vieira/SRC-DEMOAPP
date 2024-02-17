package com.example.demo.controller;

import com.example.demo.controller.dto.AtualizaBeneficiarioFormDTO;
import com.example.demo.controller.dto.BeneficiarioDTO;
import com.example.demo.controller.dto.BeneficiarioFormDTO;
import com.example.demo.entity.Beneficiario;
import com.example.demo.repository.BeneficiarioRepository;
import com.example.demo.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Transactional
    @PostMapping
    public BeneficiarioDTO salvar(@RequestBody BeneficiarioFormDTO form) {
        Beneficiario beneficiario = form.converter();
        beneficiarioRepository.save(beneficiario);
        return new BeneficiarioDTO(beneficiario);
    }

    @GetMapping("/{id}")
    public BeneficiarioDTO consultaPorId(@PathVariable Long id){
        Beneficiario beneficiarioTemp = beneficiarioRepository.findById(id).get();
        return new BeneficiarioDTO(beneficiarioTemp);
    }

    @GetMapping
    public List<BeneficiarioDTO> listar() {
        List<Beneficiario> listaBeneficiarios = beneficiarioRepository.findAll();
        return BeneficiarioDTO.converter(listaBeneficiarios);
    }

    @Transactional
    @PutMapping("/{id}")
    public BeneficiarioDTO atualizar(@PathVariable Long id, @RequestBody AtualizaBeneficiarioFormDTO form) {
        final Optional<Beneficiario> optBeneficiario = beneficiarioRepository.findById(id);

        if(optBeneficiario.isPresent()){
            Beneficiario beneficiario = optBeneficiario.get();
            form.atualiza(beneficiario);
            beneficiarioRepository.save(beneficiario);
            return new BeneficiarioDTO(beneficiario);
        }

        System.out.println("Beneficiario não encontrado");
        return null;
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){

        final Optional<Beneficiario> optBeneficiario = beneficiarioRepository.findById(id);

        if (optBeneficiario.isPresent()) {
            beneficiarioRepository.deleteById(id);
        }
    }

}