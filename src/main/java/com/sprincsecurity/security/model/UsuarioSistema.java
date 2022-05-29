package com.sprincsecurity.security.model;

import com.sprincsecurity.domain.model.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


@Getter
@Setter
public class UsuarioSistema extends User {

    private Usuario usuario;
    private Collection<? extends GrantedAuthority> permissoes;

    public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities){
        super(usuario.getEmail(), usuario.getSenha(), authorities);
        this.usuario = usuario;
        this.permissoes = authorities;
    }
}
