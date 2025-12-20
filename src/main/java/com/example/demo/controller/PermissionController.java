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
    private PermissionService uas;

    @PostMapping
    public Permission createPermission(@RequestBody Permission permission) {
        return uas.createPermission(permission);
    }

    @GetMapping
    public List<Permission> getAllPermissions() {
        return uas.getAllPermissions();
    }

    @GetMapping("/{id}")
    public Permission getPermissionById(@PathVariable long id){
        return uas.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PutMapping("/{id}")
    public UserAccount updateUser(@PathVariable long id, @RequestBody UserAccount ua) {
        return uas.updateUser(id, ua);
    }

    @PutMapping("/{id}/deactivate")
    public String deactivateUser(@PathVariable long id) {
        uas.deactivateUser(id);
        return "User deactivated successfully";
    }
}
