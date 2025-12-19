package com.example.demo.serveice.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Repository.UserAccountRepository;
import com.example.demo.entity.UserAccount;
import com.example.demo.serveice.UserAccountService;

public  class UserAccountServiceimp implements UserAccountService {
    @Autowired
    UserAccountRepository uar;
    @Override
    public UserAccount createuser(UserAccount ua){
        return uar.save(ua);
    }
    @Override
    public List<UserAccount> getall(){
        return uar.findAll();
    }
    @Override
    public Optional<UserAccount> getid(long id){
        return uar.findById(id);
    }
    
    @Override
    public void deactivateUser(Long id){
        UserAccount ss=getid(id);
        ss.setActive(false);
        
    }
    
}
