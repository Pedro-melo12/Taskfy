package com.taskfy.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskfy.api.model.Projeto;

import com.taskfy.api.repository.ProjetoRepository;

@RestController
@RequestMapping(value = "/projetos")
public class ProjetosController {

    @PostMapping
    public Projeto cadastrarNovoProjeto(@RequestBody Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    @GetMapping
    public Page<Projeto> listarProjetos(Pageable paginacao) {
        return projetoRepository.findAll(paginacao);
    }

    @GetMapping("/{id}")
    public Optional<Projeto> buscarProjetoPeloId(@PathVariable("id") Long id) {
        return projetoRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Projeto atualizarUsuario(@PathVariable("id") Long id, @RequestBody Projeto projeto) {
        Optional<Projeto> projetoExistente = projetoRepository.findById(id);

        if (projetoExistente != null) {
            projetoExistente.get().setNome(projeto.getNome());
            projetoExistente.get().setDescricao(projeto.getDescricao());
            projetoExistente.get().setUsuario(projeto.getUsuario());
            return projetoRepository.save(projetoExistente.get());
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deletarProjetoPeloId(@PathVariable("id") Long id) {
        projetoRepository.deleteById(id);
        return "Projeto deletado com sucesso";
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Projeto> obterProjetosDoUsuario (@PathVariable ("idUsuario") Long idUsuario){
        return projetoRepository.findbyUsuario(idUsuario);
    }

    @Autowired
    private ProjetoRepository projetoRepository;

}
