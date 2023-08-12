package com.taskfy.api.controller;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskfy.api.model.Usuario;
import com.taskfy.api.repository.UsuarioRepository;


@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @PostMapping
    public Usuario cadastrarNovoUsuario(@RequestBody Usuario usuario){
       return usuarioRepository.save(usuario);
    }

    @GetMapping
    public Page<Usuario> listarUsuarios(Pageable paginacao){
        return usuarioRepository.findAll(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPeloId(@PathVariable("id") Long id){
     Optional<Usuario> usuario = usuarioRepository.findById(id);
        
        if (usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuario.get());


}

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable("id") Long id , @RequestBody Usuario usuario){
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if(usuarioExistente != null){
            usuarioExistente.get().setNome(usuario.getNome());
            usuarioExistente.get().setSobrenome(usuario.getSobrenome());
            usuarioExistente.get().setEmail(usuario.getEmail());
            usuarioExistente.get().setSenha(usuario.getSenha());
            return usuarioRepository.save(usuarioExistente.get());
        }
        return null;
    }

    @GetMapping("/email/{email}")
    public Optional<Usuario> buscarUsuarioPeloEmail(@PathVariable("email") String email){

        return usuarioRepository.findByEmail(email);
    }

      


    @DeleteMapping("/{id}")
    public String deletarUsuarioPeloId(@PathVariable("id") Long id){
        usuarioRepository.deleteById(id);
        return "Usuário deletado com sucesso";
    }



    @Autowired
    private UsuarioRepository usuarioRepository;

    
}
