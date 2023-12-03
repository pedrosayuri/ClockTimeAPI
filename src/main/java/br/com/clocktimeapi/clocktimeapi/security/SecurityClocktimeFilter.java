package br.com.clocktimeapi.clocktimeapi.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.clocktimeapi.clocktimeapi.providers.JWTClocktimeProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityClocktimeFilter extends OncePerRequestFilter {

    @Autowired
    private JWTClocktimeProvider jwtClocktimeProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String header = request.getHeader("Authorization");

        if (request.getRequestURI().contains("/timekeeping")) {
            if (header != null) {
                var clocktimeToken = this.jwtClocktimeProvider.validateClocktimeToken(header);
                if (clocktimeToken == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                String userUid = jwtClocktimeProvider.getUserUid(clocktimeToken);
                String roles = jwtClocktimeProvider.getRoles(clocktimeToken);

                var grants = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + roles));
            
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userUid, null, grants);

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        
        filterChain.doFilter(request, response);
    }
}
