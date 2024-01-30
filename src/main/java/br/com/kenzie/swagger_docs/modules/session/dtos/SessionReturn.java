package br.com.kenzie.swagger_docs.modules.session.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SessionReturn {
    private final String token;
}
