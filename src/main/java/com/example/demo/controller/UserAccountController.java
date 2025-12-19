package com.example.demo.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
@RestController
@RequestMapping("/api/users")
public class UserAccountController {

    @Autowired
    UserAccountService uas;

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
        return uas.getid(id)
                  .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable long id, @RequestBody UserAccount ua) {
        return uas.updateUser(id,ua);
    }

    @PutMapping("/{id}/deactivate")
    public String deactivateUser(@PathVariable long id) {
        return uas.deactivateUser(id);
    }
}
