package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Permission;
import com.example.demo.service.PermissionService;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionService service;

    @PostMapping
    public Permission createPermission(@RequestBody Permission permission) {
        return service.createPermission(permission);
    }

    @GetMapping
    public List<Permission> getAllPermissions() {
        return service.getAllPermissions();
    }

    @GetMapping("/{id}")
    public Permission getPermissionById(@PathVariable Long id) {
        return service.getPermissionById(id); // ✅ NO orElseThrow
    }

    @PutMapping("/{id}")
    public Permission updatePermission(
            @PathVariable Long id,
            @RequestBody Permission permission) {
        return service.updatePermission(id, permission);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivatePermission(@PathVariable Long id) {
        service.deactivatePermission(id);
    }
}
