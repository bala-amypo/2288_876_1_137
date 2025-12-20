package com.example.demo.service.imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;

@Service
public class Serviceimp implements UserAccountService {

    @Autowired
    private UserAccountRepository uar;

    @Override
    public UserAccount createUser(UserAccount ua) {
        ua.setCreatedAt(LocalDateTime.now());
        ua.setUpdatedAt(LocalDateTime.now());
        return uar.save(ua);
    }

    @Override
    public List<UserAccount> getAllUsers() {
        return uar.findAll();
    }

    @Override
    public Optional<UserAccount> getUserById(long id) {
        return uar.findById(id);
    }

    @Override
    public UserAccount updateUser(long id, UserAccount ua) {
        UserAccount existing = uar.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existing.setFullname(ua.getFullname());
        existing.setUpdatedAt(LocalDateTime.now());

        return uar.save(existing);
    }

    @Override
    public void deactivateUser(long id) {
        UserAccount user = uar.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setActive(false);
        user.setUpdatedAt(LocalDateTime.now());
        uar.save(user);
    }
}
