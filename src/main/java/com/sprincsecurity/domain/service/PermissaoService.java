package com.sprincsecurity.domain.service;

import com.sprincsecurity.domain.model.Permissao;
import com.sprincsecurity.domain.repository.PermissaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<Permissao> findAll(Pageable pageable){
        return permissaoRepository.findAll(pageable);
    }
}
