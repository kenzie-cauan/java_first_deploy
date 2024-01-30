package br.com.kenzie.swagger_docs.modules.user.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreate {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
    private Boolean admin = false;
}
