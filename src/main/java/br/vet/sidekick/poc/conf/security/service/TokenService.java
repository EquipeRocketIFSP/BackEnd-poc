package br.vet.sidekick.poc.conf.security.service;

import br.vet.sidekick.poc.controller.model.Funcionario;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class TokenService {
    @Value("${app.jwt.expiration}")
    private String EXPIRATION;
    @Value("${app.jwt.secret}")
    private String SECRET;
    private Date today = new Date();

    public String create(Authentication auth) {
        return Jwts.builder()
                .setIssuer("CertVet")
                .setSubject(((Funcionario) auth.getPrincipal()).getId().toString())
                .setIssuedAt(today)
                .setExpiration(new Date(today.getTime() + Long.parseLong(EXPIRATION)))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public Boolean validate(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
            log.info("Token validado com sucesso!");
            return true;
        } catch (
                MalformedJwtException
                | ExpiredJwtException
                | UnsupportedJwtException
                | SignatureException
                | IllegalArgumentException e
        ){
            log.error(e.getLocalizedMessage());
        }
        return false;
    }

    public Long getFuncionarioId(String token) {
        return Long.parseLong(
                Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject()
        );
    }
}
