package com.sprincsecurity.resource;


import com.sprincsecurity.domain.model.Permissao;
import com.sprincsecurity.domain.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissao")
public class PermissaoController {

    @Autowired
    private PermissaoService permissaoService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO')")
    public ResponseEntity<Page<Permissao>> findAll(Pageable pageable){
        return ResponseEntity.ok(permissaoService.findAll(pageable));
    }
}
