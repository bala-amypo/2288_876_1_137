
// package com.example.demo.service;

// import java.util.List;
// import com.example.demo.entity.Role;

// public interface RoleService {

//     Role createRole(Role role);

//     Role updateRole(Long id, Role role);

//     Role getRoleById(Long id);

//     List<Role> getAllRoles();

//     Role deactivateRole(Long id);
// }


package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Role;

public interface RoleService {

    Role createRole(Role role);

    List<Role> getAllRoles();

    Role getRoleById(long id);

    Role updateRole(long id, Role role);

    void deactivateRole(long id);
}
