package com.example.demo.controller;
@RestController
@RequestMapping("/api/users")
public class UserAccountController{
    @AutoWired 
    @PostMapping("/api/users/pot")
    public UserAccount createuser(@RequestBody UserAccount ua){

    }
}