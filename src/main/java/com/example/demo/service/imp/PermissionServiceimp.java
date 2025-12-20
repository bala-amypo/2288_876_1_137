package com.example.demo.service.imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Permission;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;

@Service
public class RoleServiceimp implements RoleService {

    @Autowired
    private RoleRepository uar;

    @Override
    public Role createRole(Role role) {
        return uar.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return uar.findAll();
    }

    @Override
    public Role getRoleById(long id) {
        return uar.findById(id);
    }

    @Override
    public Role updateRole(long id, Role role) {
        Role existing = uar.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        return uar.save(existing);
    }

    @Override
    public void deactivateRole(long id) {
        Role user = uar.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setActive(false);
        uar.save(user);
    }
}
