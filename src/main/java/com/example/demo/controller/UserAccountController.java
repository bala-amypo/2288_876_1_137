package com.example.demo.controller;
@RestController
@RequestMapping("/api/users")
public class UserAccountController{
    @AutoWired 
    UserAccountService uas;
    @PostMapping("/api/users/pot")
    public UserAccount createuser(@RequestBody UserAccount ua){
        return uas.createuser(ua);
    }
    @GetMapping("/api/users/gt")
    public list<UserAccount> getall(){
        uas.getall();
    }
    @GetMapping("/api/users/gt{id}")
    public list<UserAccount> getid(@){
        uas.getall();
    }

    public optional<UserAccount>updateuser
}