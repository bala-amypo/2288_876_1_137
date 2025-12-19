package com.example.demo.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
@Service
public class UserAccountServiceimp implements UserAccountService {

    @Autowired
    private UserAccountRepository uar;

    @Override
    public UserAccount createUser(UserAccount ua) {
        return uar.save(ua);
    }

    @Override
    public List<UserAccount> getAllUsers() {
        return uar.findAll();
    }
    @Override
    public UserAccount getUserById(long id) {
        return uar.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    @Override
    public UserAccount updateUser(long id, UserAccount ua) {
        UserAccount existing = getUserById(id);

        existing.setFullname(ua.getFullname());

        return uar.save(existing);
    }

    @Override
    public void deactivateUser(long id) {
        UserAccount user = getUserById(id);
        user.setActive(false);
        uar.save(user);
    }
}
