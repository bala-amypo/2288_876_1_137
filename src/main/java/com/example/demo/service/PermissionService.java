package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Role;

public interface RoleService {

    Permission createPermission(Permission permission);

    List<Permission> getAllPermissions();

    Permission getPermissionById(Long id);

    Permission updatePermission(Long id, Permission permission);

    void deactivatePermission(Long id);
}
