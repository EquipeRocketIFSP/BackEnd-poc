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
            jwtDecode(token);
            log.info("Token validado com sucesso!");
            return true;
        } catch (MalformedJwtException e){
            log.error("MalformedJwtException");
            log.error(e.getLocalizedMessage());
        } catch (ExpiredJwtException e) {
            log.error("ExpiredJwtException");
            log.error(e.getLocalizedMessage());
        }catch (UnsupportedJwtException e){
            log.error("UnsupportedJwtException");
            log.error(e.getLocalizedMessage());
        }catch (SignatureException e) {
            log.error("SignatureException");
            log.error(e.getLocalizedMessage());
        }catch (IllegalArgumentException e){
            log.error("IllegalArgumentException");
            log.error(e.getLocalizedMessage());
        }catch (Exception e){
            log.error("Exception");
            log.error(e.getLocalizedMessage());
        }
        return false;
    }

    private Jws<Claims> jwtDecode(String token) {
        return Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token);
    }

    public Long getFuncionarioId(String token) {
        return Long.parseLong(
                jwtDecode(token)
                        .getBody()
                        .getSubject()
        );
    }
}
