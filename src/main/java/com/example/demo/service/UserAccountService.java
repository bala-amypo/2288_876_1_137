
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.UserAccount;

public interface UserAccountService {

    public UserAccount createUser(UserAccount ua);
    public List<UserAccount> getAllUsers();
    public Optional<UserAccount> getUserById(long id);
    public void deactivateUser(long id);
    public String updateUser(long id,UserAccount ua);
}
