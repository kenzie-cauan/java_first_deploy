package br.com.kenzie.swagger_docs.config;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class JwtConfig {
    private final String secretKey = genValidSecretKey("chave_secreta");
    private final long expiresInMs = hourToMs(1);

    private long hourToMs(long hours) {
        return hours * 60 * 60 * 1000;
    }

    private String genValidSecretKey(String secretKey) {
        var repeats = 1;

        while (secretKey.length() < 22) {
            secretKey = secretKey.repeat(repeats);
            repeats++;
        }

        return secretKey;
    }
}
