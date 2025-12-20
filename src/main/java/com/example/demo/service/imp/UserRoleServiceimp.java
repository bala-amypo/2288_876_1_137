package com.example.demo.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.UserAccount;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;

@Service
public class UserRoleServiceimp implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;

    // ✅ Constructor injection (ORDER AS REQUESTED)
    public UserRoleServiceimp(
            UserRoleRepository userRoleRepository,
            UserAccountRepository userAccountRepository,
            RoleRepository roleRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userAccountRepository = userAccountRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserRole assignRole(UserRole mapping) {

        UserAccount user = userAccountRepository.findById(mapping.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!Boolean.TRUE.equals(user.getActive())) {
            throw new RuntimeException("User is inactive");
        }

        Role role = roleRepository.findById(mapping.getRole().getId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        if (!Boolean.TRUE.equals(role.getActive())) {
            throw new RuntimeException("Role is inactive");
        }

        mapping.setUser(user);
        mapping.setRole(role);

        return userRoleRepository.save(mapping);
    }

    @Override
    public List<UserRole> getRolesForUser(Long userId) {
        return userRoleRepository.findByUser_Id(userId);
    }

    @Override
    public UserRole getMappingById(Long id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserRole mapping not found"));
    }

    @Override
    public void removeRole(Long mappingId) {
        UserRole mapping = getMappingById(mappingId);
        userRoleRepository.deleteById(mapping.getId());
    }
}
