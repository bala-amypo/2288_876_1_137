package com.explain.demo.entity;
@Entity
public class UserAccount{
    @Id
    private long id;
    @column(unique=true)
    private String email;
    private String fullname;
    private Boolean active;
    private Timestamp create;


}