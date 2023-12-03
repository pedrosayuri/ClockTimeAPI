package br.com.clocktimeapi.clocktimeapi.security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.clocktimeapi.clocktimeapi.providers.JWTTimekeepingProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityTimekeepingFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTimekeepingProvider jwtTimekeepingProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String header = request.getHeader("Authorization");

        if (request.getRequestURI().contains("/timekeeping")) {
            if (header != null) {
                var token = this.jwtTimekeepingProvider.validateToken(header);
                if (token == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                var userId = token.getSubject();
                LocalDateTime userIssuedAt = LocalDateTime.ofInstant(
                    token.getIssuedAt().toInstant(), ZoneId.systemDefault());

                request.setAttribute("user_id", userId);
                request.setAttribute("user_issued_at", userIssuedAt);

                var userRoles = token.getClaim("roles").asList(String.class);

                var userGrants = userRoles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toString()))
                    .toList();
            
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token, null, userGrants);

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        
        filterChain.doFilter(request, response);
    }
    
}
