package com.gremio.service;

import com.gremio.model.ERole;
import com.gremio.model.Role;
import com.gremio.repository.RoleRepository;
import com.gremio.service.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    RoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(ERole roleName) {
        return roleRepository.findByName(roleName);
    }
}
