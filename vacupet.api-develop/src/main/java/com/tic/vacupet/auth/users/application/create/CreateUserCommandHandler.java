package com.tic.vacupet.auth.users.application.create;

import com.tic.vacupet.auth.users.domain.User;
import com.tic.vacupet.auth.users.infrastructure.UserRepository;
import io.jkratz.mediator.core.CommandHandler;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserCommandHandler(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void handle(CreateUserCommand command) {
        val user = User.builder()
                .username(command.username())
                .password(passwordEncoder.encode(command.password()))
                .email(command.email())
                .build();

        userRepository.save(user);
    }
}
