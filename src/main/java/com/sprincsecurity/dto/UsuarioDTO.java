package com.sprincsecurity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;


@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO implements Serializable {

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String email;


    @NotNull
    @NotBlank
    private String senha;


    @Valid
    @NotEmpty
    @NotNull
    private Set<PermissaoUsuarioDTO> permissao ;
}
