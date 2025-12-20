package com.example.demo.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.entity.RolePermission;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RolePermissionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RolePermissionService;

@Service
public class RolePermissionServiceimp implements RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    // ✅ Constructor injection (ORDER AS REQUESTED)
    public RolePermissionServiceimp(
            RolePermissionRepository rolePermissionRepository,
            RoleRepository roleRepository,
            PermissionRepository permissionRepository) {
        this.rolePermissionRepository = rolePermissionRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public RolePermission grantPermission(RolePermission mapping) {

        Role role = roleRepository.findById(mapping.getRole().getId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        if (!Boolean.TRUE.equals(role.getActive())) {
            throw new RuntimeException("Role is inactive");
        }

        Permission permission = permissionRepository.findById(mapping.getPermission().getId())
                .orElseThrow(() -> new RuntimeException("Permission not found"));

        if (!Boolean.TRUE.equals(permission.getActive())) {
            throw new RuntimeException("Permission is inactive");
        }

        mapping.setRole(role);
        mapping.setPermission(permission);

        return rolePermissionRepository.save(mapping);
    }

    @Override
    public List<RolePermission> getPermissionsForRole(Long roleId) {
        return rolePermissionRepository.findByRole_Id(roleId);
    }

    @Override
    public RolePermission getMappingById(Long id) {
        return rolePermissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RolePermission mapping not found"));
    }

    @Override
    public void revokePermission(Long mappingId) {
        RolePermission mapping = getMappingById(mappingId);
        rolePermissionRepository.deleteById(mapping.getId());
    }
}
