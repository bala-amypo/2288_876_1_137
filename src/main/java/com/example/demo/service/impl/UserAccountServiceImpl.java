package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    // ✅ Constructor injection (SAAS rule)
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    // ✅ EXACT signature match (long)
    @Override
    public UserAccount getUserById(long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserAccount> getAllUsers() {
        return userAccountRepository.findAll();
    }

    // ✅ EXACT signature match (long)
    @Override
    public void deactivateUser(long id) {
        UserAccount user = getUserById(id);
        user.setActive(false);
        userAccountRepository.save(user);
    }
}