package com.gremio.service.interfaces;

import com.gremio.model.User;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findEmailAndUsernameAndNameAndRolesByUsername(String username);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findUserByEmail(String email);
    Optional<User> findById(Long id);
    User create(User user);
    void save(User user);
}