package com.gremio.service.interfaces;

import com.gremio.model.ERole;
import com.gremio.model.Role;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(ERole roleName);
}
