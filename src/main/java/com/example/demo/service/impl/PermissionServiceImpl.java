package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Permission;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    // ✅ Constructor Injection (SAAS rule)
    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Permission createPermission(Permission permission) {
        permission.setActive(true);
        return permissionRepository.save(permission);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
    }

    @Override
    public Permission updatePermission(Long id, Permission permission) {
        Permission existing = getPermissionById(id);

        existing.setPermissionKey(permission.getPermissionKey());
        existing.setDescription(permission.getDescription());

        return permissionRepository.save(existing);
    }

    @Override
    public void deactivatePermission(Long id) {
        Permission permission = getPermissionById(id);

        permission.setActive(false);
        permissionRepository.save(permission);
    }
}