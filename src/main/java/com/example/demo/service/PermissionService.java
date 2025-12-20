package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Role;

public interface RoleService {

    Permission createPermission(Permission permission);

    List<Permission> getAllPermissions();

    Optional<Role> getRoleById(long id);

    Role updateRole(long id, Role role);

    void deactivateRole(long id);
}
