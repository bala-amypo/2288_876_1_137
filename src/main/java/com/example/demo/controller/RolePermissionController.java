package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService uas;

    @PostMapping
    public Role createRole(@RequestBody Role ua) {
        return uas.createRole(ua);
    }

    @GetMapping
    public List<UserAccount> getAllRoles() {
        return uas.getAllRoles();
    }

    @GetMapping("/{id}")
    public UserAccount getRoleById(@PathVariable long id) {
        return uas.getRoleById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PutMapping("/{id}")
    public UserAccount updateUser(@PathVariable long id, @RequestBody UserAccount ua) {
        return uas.updateUser(id, ua);
    }

    @PutMapping("/{id}/deactivate")
    public String deactivateRole(@PathVariable long id) {
        uas.deactivateRole(id);
        return "User deactivated successfully";
    }
}
