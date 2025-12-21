package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    Map<Long, Role> mp = new HashMap<>();

    public Role createRole(Role role) {
        mp.put(role.getId(), role);
        return role;
    }

    public Role updateRole(Long id, Role role) {
        mp.replace(id, role);
        return role;
    }

    public Role getRoleById(Long id) {
        return mp.get(id);
    }

    public List<Role> getAllRoles() {
        return new ArrayList<>(mp.values());
    }

    public Role deactivateRole(Long id) {
        Role role = mp.get(id);
        if (role != null) {
            role.setActive(false);
            mp.put(id, role);
        }
        return role;
    }
}









// package com.example.demo.service.impl;

// import java.util.List;

// import org.springframework.stereotype.Service;

// import com.example.demo.entity.Role;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.RoleRepository;
// import com.example.demo.service.RoleService;

// @Service
// public class RoleServiceImpl implements RoleService {

//     private final RoleRepository roleRepository;

//     // ✅ REQUIRED CONSTRUCTOR (SAAS RULE)
//     public RoleServiceImpl(RoleRepository roleRepository) {
//         this.roleRepository = roleRepository;
//     }

//     @Override
//     public Role createRole(Role role) {
//         return roleRepository.save(role);
//     }

//     @Override
//     public List<Role> getAllRoles() {
//         return roleRepository.findAll();
//     }

//     @Override
//     public Role getRoleById(long id) {
//         return roleRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
//     }

//     @Override
//     public Role updateRole(long id, Role role) {
//         Role existing = getRoleById(id);
//         existing.setRoleName(role.getRoleName());
//         existing.setDescription(role.getDescription());
//         return roleRepository.save(existing);
//     }

//     @Override
//     public void deactivateRole(long id) {
//         Role role = getRoleById(id);
//         role.setActive(false);
//         roleRepository.save(role);
//     }
// }
