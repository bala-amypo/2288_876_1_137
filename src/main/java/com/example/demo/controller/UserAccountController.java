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

    // CREATE
    @PostMapping
    public UserAccount createUser(@RequestBody UserAccount ua) {
        return uas.createUser(ua);
    }

    // GET ALL
    @GetMapping
    public List<UserAccount> getAll() {
        return uas.getall();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public UserAccount getById(@PathVariable long id) {
        return uas.getid(id)
                  .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // UPDATE
    @PutMapping("/{id}")
    public String updateUser(@PathVariable long id, @RequestBody UserAccount ua) {
        UserAccount existing = uas.getid(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existing.setEmail(ua.getEmail());
        existing.setFullname(ua.getFullname());

        uas.createuser(existing);
        return "Data Updated Successfully";
    }

    // DEACTIVATE
    @PutMapping("/{id}/deactivate")
    public String deactivateUser(@PathVariable long id) {
        uas.deactivateUser(id);
        return "User deactivated successfully";
    }
}
