package com.gremio.service.interfaces;

import com.gremio.model.dto.UserDetailsDto;
import com.gremio.persistence.entity.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findUserByEmail(String email);
    Optional<UserDetailsDto> findById(Long id);
    User create(User user);
    void save(User user);
    Page<UserDetailsDto> getAllUser(Pageable pageable);
}