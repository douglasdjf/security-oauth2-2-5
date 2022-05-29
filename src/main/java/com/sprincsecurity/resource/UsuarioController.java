package com.sprincsecurity.resource;

import com.sprincsecurity.domain.model.Usuario;
import com.sprincsecurity.domain.service.UsuarioService;
import com.sprincsecurity.dto.UsuarioDTO;
import com.sprincsecurity.dto.UsuarioUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO')")
    public ResponseEntity<Page<Usuario>> findAll(Pageable pageable){
        return ResponseEntity.ok(usuarioService.findAll(pageable));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_CRIAR_USUARIO')")
    public ResponseEntity<UsuarioDTO> createUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO){
            return ResponseEntity.ok(usuarioService.create(usuarioDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE_USUARIO')")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id){
        usuarioService.deleteById(id);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ALTERAR_USUARIO')")
    public ResponseEntity<UsuarioUpdateDTO> alterarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateDTO usuarioUpdateDTO){
        UsuarioUpdateDTO usuarioUpdate = usuarioService.update(id, usuarioUpdateDTO);
        return  ResponseEntity.ok(usuarioUpdate);
    }
}
