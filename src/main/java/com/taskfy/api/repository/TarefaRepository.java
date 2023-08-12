package com.taskfy.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.taskfy.api.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    @Query("SELECT t FROM tb_tarefas t WHERE t.projeto.idProjeto = :id")

    List<Tarefa> findByProjeto(@Param("id") Long idProjeto);

    @Query("SELECT t FROM tb_tarefas t WHERE t.usuario.idUsuario = :id")
    List<Tarefa> findByUsuario(@Param("id") Long idUsuario);
}
