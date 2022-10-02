package com.tic.vacupet.auth.users.application.create;

import io.jkratz.mediator.core.Command;

public record CreateUserCommand(String username, String password, String email) implements Command {
}
