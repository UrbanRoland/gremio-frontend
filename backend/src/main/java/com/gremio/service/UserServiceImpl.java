package com.gremio.service;

import com.gremio.enums.RoleType;
import com.gremio.exception.NotFoundException;
import com.gremio.persistence.entity.User;
import com.gremio.repository.UserRepository;
import com.gremio.service.interfaces.UserService;
import jakarta.validation.ValidationException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        return Optional.ofNullable(userRepository.findUserByEmail(email)).orElseThrow(() -> new NotFoundException("NOT FOUND"));
    }
    @Override
    public User findEmailAndUsernameAndNameAndRolesByUsername(final String username) {
        return userRepository.findEmailAndUsernameAndNameAndRolesByUsername(username);
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(final String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(final String email) {
        return userRepository.existsByEmail(email);
    }
  
    @Override
    public User findUserByEmail(final String email) {
        return userRepository.findUserByEmail(email);
    }
  
    @Override
    public Optional<User> findById(final Long id) {
        return userRepository.findById(id);
    }
  
    @Override
    public User create(final User user) {
        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new ValidationException("email already taken");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(RoleType.ROLE_READ_ONLY);

        return userRepository.save(user);
    }
  
    @Override
    public void save(final User user) {
        userRepository.save(user);
    }
}