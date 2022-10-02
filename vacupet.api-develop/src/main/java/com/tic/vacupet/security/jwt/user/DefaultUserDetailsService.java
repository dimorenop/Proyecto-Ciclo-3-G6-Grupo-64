package com.tic.vacupet.security.jwt.user;

import com.tic.vacupet.auth.users.application.find_by_name.FindUserByNameQuery;
import io.jkratz.mediator.core.Mediator;
import lombok.val;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    private final Mediator mediator;

    public DefaultUserDetailsService(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        val found = mediator.dispatch(new FindUserByNameQuery(username));

        if (found.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        val user = found.get();

        return User.create(user.username(), user.password(), user.email());
    }
}
