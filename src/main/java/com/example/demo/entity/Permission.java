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
    
}