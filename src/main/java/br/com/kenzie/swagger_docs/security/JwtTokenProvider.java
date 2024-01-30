package br.com.kenzie.swagger_docs.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import br.com.kenzie.swagger_docs.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private static final String AUTHORITIES_KEY = "roles";

    @Autowired
    private JwtConfig jwtConfig;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        var secret = Base64.getEncoder().encodeToString(jwtConfig.getSecretKey().getBytes());
        secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(Authentication authentication) {
        var username = authentication.getName();
        var authorities = authentication.getAuthorities();
        var claimsBuilder = Jwts.claims().subject(username);

        if (authorities.isEmpty() == false) {
            var authoritiesCollector = authorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));

            claimsBuilder.add(AUTHORITIES_KEY, authoritiesCollector);
        }

        var claims = claimsBuilder.build();
        var now = new Date();
        var expiresIn = new Date(now.getTime() + jwtConfig.getExpiresInMs());

        return Jwts.builder().claims(claims)
                .issuedAt(now).expiration(expiresIn)
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();

        Object authoritiesClaim = claims.get(AUTHORITIES_KEY);

        var authorities = authoritiesClaim == null ? AuthorityUtils.NO_AUTHORITIES
                : AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesClaim.toString());

        var principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String token) {
        Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
        return true;
    }
}