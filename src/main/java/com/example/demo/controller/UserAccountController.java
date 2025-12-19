package com.example.demo.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
@RestController

@RequestMapping("/api/users")
public class UserAccountController{
    @Autowired
    UserAccountService uas;
    @PostMapping
    public UserAccount createuser(@RequestBody UserAccount ua){
        return uas.createuser(ua);
    }
    @GetMapping
    public List<UserAccount> getall(){
        return uas.getall();
    }
    @GetMapping("/{id}")
    public Optional<UserAccount> getid(@PathVariable long id){
        return uas.getid(id);
    }
    @PutMapping("/{id}")
    public String updata(@PathVariable long id,@RequestBody UserAccount ua){
        Optional<UserAccount> UserA = uas.getid(id);

        if(UserA.isPresent()){
            ua.setId(id);
            uas.createuser(ua);

            return "Data Updated Successfully";
        }
        else{

            return id+ "not found";
        }
    }
    @PutMapping("/{id}")
    public void deactivateUser(@PathVariable Long id) {
        uas.deactivateUser(id);
    }

}