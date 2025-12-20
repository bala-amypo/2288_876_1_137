package com.example.demo.entity;
package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Role {

    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String roleName;

    private String description;
    private Boolean active = true;
}