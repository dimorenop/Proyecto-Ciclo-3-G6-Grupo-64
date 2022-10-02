package com.tic.vacupet.security.jwt;

import com.tic.vacupet.auth.users.application.find_by_name.FindUserByNameQuery;
import io.jkratz.mediator.core.Mediator;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    final JwtProvider jwtProvider;
    final Mediator mediator;

    public JwtTokenFilter(JwtProvider jwtProvider, Mediator mediator) {
        this.jwtProvider = jwtProvider;
        this.mediator = mediator;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            val token = getToken(request);
            if (token != null && jwtProvider.validateToken(token)) {
                val username = jwtProvider.getUsernameFromToken(token);

                val user = mediator.dispatch(new FindUserByNameQuery(username));

                if (user.isPresent()) {
                    val auth = new UsernamePasswordAuthenticationToken(user.get().username(), null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception e) {
            log.error("fail in the doFilterInternal method of JwtTokenFilter", e);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}
