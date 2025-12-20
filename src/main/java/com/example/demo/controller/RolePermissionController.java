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
    public UserAccount createUser(@RequestBody UserAccount ua) {
        return uas.createUser(ua);
    }

    @GetMapping
    public List<UserAccount> getAllUsers() {
        return uas.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserAccount getUserById(@PathVariable long id) {
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
