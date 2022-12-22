package com.gremio.service.interfaces;

import com.gremio.model.User;

import java.util.Optional;

public interface IUserDetailsService {

    User findEmailAndUsernameAndNameAndRolesByUsername(String username);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    void save(User user);
}
