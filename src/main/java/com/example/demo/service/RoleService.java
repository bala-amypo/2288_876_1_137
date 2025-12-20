package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Role;

public interface RoleService {

    Role createRole(Role role);

    List<Role> getAllRoles();

    Optional<UserAccount> getUserById(long id);

    UserAccount updateUser(long id, UserAccount ua);

    void deactivateUser(long id);
}
