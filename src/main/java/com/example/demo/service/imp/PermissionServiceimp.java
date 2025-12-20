package com.example.demo.service.imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Permission;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.PermissionService;

@Service
public class PermissionServiceimp implements PermissionService {

    @Autowired
    private PermissionRepository uar;

    @Override
    public Permission createPermission(Permission permission){
        return uar.save(permission);
    }

    @Override
    public List<Permission> getAllPermissions(){
        return uar.findAll();
    }

    @Override
    public Permission getPermissionById(long id){
        return uar.findById(id);
    }

    @Override
    public Permission updatePermission(long id, Permission permission){
        Role existing = uar.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        return uar.save(existing);
    }

    @Override
    public void deactivatePermission(Long id){
        Role permission = uar.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        permission.setActive(false);
        uar.save(permission);
    }
}
