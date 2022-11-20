package br.vet.sidekick.poc.conf.security.service;

import br.vet.sidekick.poc.model.Funcionario;
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
        log.debug("TokenService#create");
        return Jwts.builder()
                .setIssuer("CertVet")
                .setSubject(((Funcionario) auth.getPrincipal()).getId().toString())
                .setIssuedAt(today)
                .setExpiration(new Date(today.getTime() + Long.parseLong(EXPIRATION)))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public Boolean validate(String token) {
        log.debug("TokenService#validate");
        try {
            jwtDecode(token);
            log.info("Token validado com sucesso!");
            return true;
        } catch (MalformedJwtException e){
            log.error("MalformedJwtException: " + e.getLocalizedMessage());
        } catch (ExpiredJwtException e) {
            log.error("ExpiredJwtException: " + e.getLocalizedMessage());
        }catch (UnsupportedJwtException e){
            log.error("UnsupportedJwtException: " + e.getLocalizedMessage());
        }catch (SignatureException e) {
            log.error("SignatureException");
            log.error(e.getLocalizedMessage());
        }catch (IllegalArgumentException e){
            log.error("IllegalArgumentException: " + e.getLocalizedMessage());
        }catch (Exception e){
            log.error("Exception: " + e.getLocalizedMessage());
        }
        log.info("Login sem sucesso");
        return false;
    }

    public Jws<Claims> jwtDecode(String token) {
        log.debug("TokenService#jwtDecode");
        return Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token);
    }

    public Long getFuncionarioId(String token) {
        log.debug("TokenService#getFuncinario");
        return Long.parseLong(
                jwtDecode(token)
                        .getBody()
                        .getSubject()
        );
    }
}
