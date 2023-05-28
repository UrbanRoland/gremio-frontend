package com.gremio.service;

import com.gremio.enums.RoleType;
import com.gremio.exception.NotFoundException;
import com.gremio.model.dto.UserDetailsDto;
import com.gremio.persistence.entity.User;
import com.gremio.repository.UserRepository;
import com.gremio.service.interfaces.UserService;
import jakarta.validation.ValidationException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    
    private final ConversionService conversionService;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        return Optional.ofNullable(userRepository.findUserByEmail(email)).orElseThrow(() -> new NotFoundException("NOT FOUND"));
    }

    @Override
    public User findUserByEmail(final String email) {
        return userRepository.findUserByEmail(email);
    }
  
    @Override
    public Optional<UserDetailsDto> findById(final Long id) {
        return userRepository.findById(id).map(user -> conversionService.convert(user, UserDetailsDto.class));
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
    
    @Override
    public Page<UserDetailsDto> getAllUser(final Pageable pageable) {
       return userRepository.findAll(pageable).map(user -> conversionService.convert(user, UserDetailsDto.class));
    }
}