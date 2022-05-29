package com.sprincsecurity.security.service;

import com.sprincsecurity.domain.model.Usuario;
import com.sprincsecurity.domain.repository.UsuarioRepository;
import com.sprincsecurity.security.model.UsuarioSistema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("loadUserByUsername" + email);
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        Usuario usuario = usuarioOptional.orElseThrow(() -> new  UsernameNotFoundException("Usuário e/ou senha inválido"));

        return new UsuarioSistema(usuario,getPermissao(usuario));
    }

    private Collection<? extends GrantedAuthority> getPermissao(Usuario usuario){
        log.info("getPermissao");
        Set<SimpleGrantedAuthority> authority = new HashSet<>();
        usuario.getPermissao().forEach(p -> authority.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));

        return authority;
    }
}
