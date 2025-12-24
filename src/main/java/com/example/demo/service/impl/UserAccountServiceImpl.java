package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;

import java.util.List;

public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount createUser(UserAccount user) {
        if (userAccountRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        user.setActive(true);
        return userAccountRepository.save(user);
    }

    @Override
    public UserAccount updateUser(Long id, UserAccount updated) {
        UserAccount user = userAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setEmail(updated.getEmail());
        user.setFullName(updated.getFullName());
        return userAccountRepository.save(user);
    }

    // MUST BE primitive long
    @Override
    public UserAccount getUserById(long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void deactivateUser(long id) {
        UserAccount user = getUserById(id);
        user.setActive(false);
        userAccountRepository.save(user);
    }

    @Override
    public List<UserAccount> getAllUsers() {
        return userAccountRepository.findAll();
    }
}
