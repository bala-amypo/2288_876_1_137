package com.explain.demo.entity;
import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserAccount{
    @Id
    private long id;
    @Column(unique=true)
    private String email;
    private String fullname;
    private Boolean active;
    private Timestamp create;
    private Timestamp updatedAt;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public Timestamp getCreate() {
        return create;
    }
    public void setCreate(Timestamp create) {
        this.create = create;
    }
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    public UserAccount(long id, String email, String fullname, Boolean active, Timestamp create, Timestamp updatedAt) {
        this.id = id;
        this.email = email;
        this.fullname = fullname;
        this.active = active;
        this.create = create;
        this.updatedAt = updatedAt;
    }
    public UserAccount() {
    }
    
}