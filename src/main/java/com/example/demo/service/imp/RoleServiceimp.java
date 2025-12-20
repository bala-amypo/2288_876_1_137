package com.example.demo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;

@Service
public class RoleServiceimp implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public Role createRole(Role role) {
        return repository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return repository.findAll();
    }

    @Override
    public Role getRoleById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public Role updateRole(long id, Role role) {
        Role existing = getRoleById(id);
        existing.setRoleName(role.getRoleName());
        existing.setDescription(role.getDescription());
        return repository.save(existing);
    }

    @Override
    public void deactivateRole(long id) {
        Role role = getRoleById(id);
        role.setActive(false);
        repository.save(role);
    }
}
