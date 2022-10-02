package com.tic.vacupet.auth.users.application.find_by_name;

import com.tic.vacupet.auth.users.application.UserResponse;
import com.tic.vacupet.auth.users.infrastructure.UserRepository;
import io.jkratz.mediator.core.RequestHandler;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindUserByNameQueryHandler implements RequestHandler<FindUserByNameQuery, Optional<UserResponse>> {

    private final UserRepository userRepository;

    public FindUserByNameQueryHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserResponse> handle(FindUserByNameQuery query) {
        val user = userRepository.findByUsername(query.username());

        if (user.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new UserResponse(user.get().getUsername(), user.get().getEmail(), user.get().getPassword()));
    }
}
