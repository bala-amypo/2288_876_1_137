package com.example.demo.service.impl;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Role;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(Role role) {
        if (roleRepository.findByRoleName(role.getRoleName()).isPresent()) {
            throw new BadRequestException("Role already exists");
        }
        role.setActive(true);
        return roleRepository.save(role);
    }

    // MUST BE primitive long
    @Override
    public Role updateRole(long id, Role updated) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        role.setRoleName(updated.getRoleName());
        role.setDescription(updated.getDescription());
        return roleRepository.save(role);
    }

    @Override
    public void deactivateRole(long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        role.setActive(false);
        roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
