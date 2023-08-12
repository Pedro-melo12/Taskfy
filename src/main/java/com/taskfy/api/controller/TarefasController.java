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

import com.taskfy.api.model.Tarefa;

import com.taskfy.api.repository.TarefaRepository;



@RestController
@RequestMapping(value = "/tarefas")
public class TarefasController {
    
    @PostMapping
    public Tarefa cadastrarNovaTarefa(@RequestBody Tarefa tarefa){
        return tarefaRepository.save(tarefa);
    }

      @GetMapping
   public Page<Tarefa> Tarefas(Pageable paginacao){
        return tarefaRepository.findAll(paginacao);
    }

      @PutMapping("/{id}")
    public Tarefa atualizarTarefa(@PathVariable("id") Long id , @RequestBody Tarefa tarefa){
        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);

        if(tarefaExistente != null){
         
               tarefaExistente.get().setNome(tarefa.getNome());
               tarefaExistente.get().setDescricao(tarefa.getDescricao());
               tarefaExistente.get().setDataDeCriacao(tarefa.getDataDeCriacao());
               tarefaExistente.get().setDataDeConclusao(tarefa.getDataDeConclusao());
               tarefaExistente.get().setPrioridade(tarefa.getPrioridade());
               tarefaExistente.get().setStatus(tarefa.getStatus());
               tarefaExistente.get().setUsuario(tarefa.getUsuario());
               tarefaExistente.get().setProjeto(tarefa.getProjeto());

            
            return tarefaRepository.save(tarefaExistente.get());
        }
        return null;
    }


    @DeleteMapping("/{id}")
    public String deletarTarefaPeloId(@PathVariable("id") Long id){
        tarefaRepository.deleteById(id);
        return "Tarefa deletada com sucesso";
    }

    @GetMapping("/projetos/{id}")
    public List<Tarefa> obterTarefasDeUmProjeto(@PathVariable("Id") Long id) {
        return tarefaRepository.findByProjeto(id);
    }

     @GetMapping("/usuario/{id}")
    public List<Tarefa> obterTarefasDeUmUsuario(@PathVariable("Id") Long id) {
        return tarefaRepository.findByUsuario(id);
    }
    @Autowired

    private TarefaRepository tarefaRepository;

}