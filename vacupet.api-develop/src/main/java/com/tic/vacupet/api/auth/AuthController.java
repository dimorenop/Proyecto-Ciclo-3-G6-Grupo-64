package com.tic.vacupet.api.auth;

import com.tic.vacupet.api.auth.requests.LoginRequest;
import com.tic.vacupet.auth.users.application.create.CreateUserCommand;
import com.tic.vacupet.security.jwt.JwtProvider;
import io.jkratz.mediator.core.Mediator;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final Mediator mediator;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, Mediator mediator, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.mediator = mediator;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        val authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.generateToken(authentication);
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody CreateUserCommand command) {
        mediator.dispatch(command);

        return ResponseEntity.ok().build();
    }
}
