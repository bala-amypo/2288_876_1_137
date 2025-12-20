package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Permission;

public interface PermissionService {

    Permission createPermission(Permission permission);

    List<Permission> getAllPermissions();

    Permission getPermissionById(Long id);

    Permission updatePermission(Long id, Permission permission);

    void deactivatePermission(Long id);
}
