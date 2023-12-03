package br.com.clocktimeapi.clocktimeapi.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.clocktimeapi.clocktimeapi.providers.JWTLoginProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityLoginFilter extends OncePerRequestFilter {

    @Autowired
    private JWTLoginProvider jwtLoginProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
   
    String header = request.getHeader("Authorization");

    if (request.getRequestURI().contains("/employee")) {
        if (header != null) {
            var loginToken = this.jwtLoginProvider.validLoginToken(header);
            if (loginToken == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            var userId = loginToken.getUserId();
            var roles = loginToken.getRoles();

            var grants = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + roles));

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userId, null, grants);

            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }
    
    filterChain.doFilter(request, response);
    
    }
    
}


