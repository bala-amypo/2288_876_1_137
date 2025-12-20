package com.example.demo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Permission{
    @Id
    private long id;
    @Column(unique=true)
    private String permissionKey;
    private String description;
    private Boolean active;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getPermissionKey() {
        return permissionKey;
    }
    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public Permission(long id, String permissionKey, String description, Boolean active) {
        this.id = id;
        this.permissionKey = permissionKey;
        this.description = description;
        this.active = active;
    }
    public Permission() {
    }
    
}