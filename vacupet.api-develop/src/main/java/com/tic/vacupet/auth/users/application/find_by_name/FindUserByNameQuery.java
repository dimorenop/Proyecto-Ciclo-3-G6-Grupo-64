package com.tic.vacupet.auth.users.application.find_by_name;

import com.tic.vacupet.auth.users.application.UserResponse;
import io.jkratz.mediator.core.Request;

import java.util.Optional;

public record FindUserByNameQuery(String username) implements Request<Optional<UserResponse>> {
}
