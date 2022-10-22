package br.vet.sidekick.poc.conf.security.service;

import br.vet.sidekick.poc.model.Funcionario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
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
}
