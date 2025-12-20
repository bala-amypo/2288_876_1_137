package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    // ✅ REQUIRED CONSTRUCTOR (SAAS RULE)
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount createUser(UserAccount ua) {
        ua.setCreatedAt(LocalDateTime.now());
        ua.setUpdatedAt(LocalDateTime.now());
        return userAccountRepository.save(ua);
    }

    @Override
    public List<UserAccount> getAllUsers() {
        return userAccountRepository.findAll();
    }

    @Override
    public UserAccount getUserById(long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserAccount updateUser(long id, UserAccount ua) {
        UserAccount existing = getUserById(id);
        existing.setFullname(ua.getFullname());
        existing.setUpdatedAt(LocalDateTime.now());
        return userAccountRepository.save(existing);
    }

    @Override
    public void deactivateUser(long id) {
        UserAccount user = getUserById(id);
        user.setActive(false);
        user.setUpdatedAt(LocalDateTime.now());
        userAccountRepository.save(user);
    }
}