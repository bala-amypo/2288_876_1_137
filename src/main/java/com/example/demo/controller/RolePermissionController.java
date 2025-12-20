package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.RolePermission;
import com.example.demo.service.RolePermissionService;

@RestController
@RequestMapping("/api/role-permissions")
public class RolePermissionController {

    private final RolePermissionService service;

    public RolePermissionController(RolePermissionService service) {
        this.service = service;
    }

    @PostMapping
    public RolePermission grantPermission(@RequestBody RolePermission mapping) {
        return service.grantPermission(mapping);
    }

    @GetMapping("/role/{roleId}")
    public List<RolePermission> getPermissionsForRole(@PathVariable Long roleId) {
        return service.getPermissionsForRole(roleId);
    }

    @GetMapping("/{id}")
    public RolePermission getMapping(@PathVariable Long id) {
        return service.getMappingById(id);
    }

    @DeleteMapping("/{id}")
    public void revokePermission(@PathVariable Long id) {
        service.revokePermission(id);
    }
}
