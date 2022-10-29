package br.vet.sidekick.poc.conf.security.filter;

import br.vet.sidekick.poc.conf.security.service.TokenService;
import br.vet.sidekick.poc.model.Funcionario;
import br.vet.sidekick.poc.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private static String TYPE = "Bearer ";

    private TokenService tokenService;

    private FuncionarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = retrieveToken(request);
        if(tokenService.validate(token)){
            authenticate(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticate(String token) throws NoSuchElementException{
        Long userId = tokenService.getFuncionarioId(token);
        log.info("Usuário logado: " + userId);
        Funcionario funcionario;
        try{
            funcionario = repository.findById(userId).orElseThrow();
        } catch (NoSuchElementException e) {
            log.error(e.getLocalizedMessage());
            throw new NoSuchElementException(e);
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, funcionario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String retrieveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith(TYPE)){
            return null;
        }
        return token.substring(TYPE.length(), token.length());
    }
}
