// package com.example.demo.entity;

// import jakarta.persistence.*;

// @Entity
// public class Role {

//     @Id
//     private Long id;

//     @Column(unique = true, nullable = false)
//     private String roleName;

//     private String description;
//     private Boolean active = true;

//     public Role() {}

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public String getRoleName() { return roleName; }
//     public void setRoleName(String roleName) { this.roleName = roleName; }

//     public String getDescription() { return description; }
//     public void setDescription(String description) { this.description = description; }

//     public Boolean getActive() { return active; }
//     public void setActive(Boolean active) { this.active = active; }
//     public Role(long id, String roleName, String description, Boolean active) {
//         this.id = id;
//         this.roleName = roleName;
//         this.description = description;
//         this.active = active;
//     }
//     public Role() {
//     }
// }
package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String roleName;

    private String description;

    private Boolean active = true;

    // ✅ ONLY ONE default constructor
    public Role() {}

    // ✅ Parameterized constructor
    public Role(Long id, String roleName, String description, Boolean active) {
        this.id = id;
        this.roleName = roleName;
        this.description = description;
        this.active = active;
    }

    // getters & setters
}
