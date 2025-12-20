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
    private RoleService service;

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return service.createRole(role);
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return service.getAllRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable long id) {
        return service.getRoleById(id);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable long id, @RequestBody Role role) {
        return service.updateRole(id, role);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateRole(@PathVariable long id) {
        service.deactivateRole(id);
    }
}
