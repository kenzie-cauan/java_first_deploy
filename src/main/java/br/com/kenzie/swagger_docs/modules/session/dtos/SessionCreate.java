package br.com.kenzie.swagger_docs.modules.session.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SessionCreate {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
