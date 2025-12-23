// package com.example.demo.service.impl;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import org.springframework.stereotype.Service;

// import com.example.demo.entity.UserRole;
// import com.example.demo.service.UserRoleService;

// @Service
// public class UserRoleServiceImpl implements UserRoleService {

//     Map<Long, UserRole> mp = new HashMap<>();

//     public UserRole assignRole(UserRole userRole) {
//         mp.put(userRole.getId(), userRole);
//         return userRole;
//     }

//     public List<UserRole> getRolesForUser(Long userId) {
//         return new ArrayList<>(mp.values());
//     }

//     public UserRole getMappingById(Long id) {
//         return mp.get(id);
//     }

//     public void removeRole(Long id) {
//         mp.remove(id);
//     }
// }








package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.UserAccount;
import com.example.demo.entity.UserRole;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;

    
    public UserRoleServiceImpl(
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
                .orElseThrow(() -> new BadRequestException("User not found"));

        if (!Boolean.TRUE.equals(user.getActive())) {
            throw new BadRequestException("User is inactive");
        }

        Role role = roleRepository.findById(mapping.getRole().getId())
                .orElseThrow(() -> new BadRequestException("Role not found"));

        if (!Boolean.TRUE.equals(role.getActive())) {
            throw new BadRequestException("Role is inactive");
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
                .orElseThrow(() -> new ResourceNotFoundException("UserRole mapping not found"));
    }

    @Override
    public void removeRole(Long mappingId) {
        UserRole mapping = getMappingById(mappingId);
        userRoleRepository.deleteById(mapping.getId());
    }
}
