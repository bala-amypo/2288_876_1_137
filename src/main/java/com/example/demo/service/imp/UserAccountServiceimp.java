package com.example.demo.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
@Service
public  class UserAccountServiceimp implements UserAccountService {
    @Autowired
    UserAccountRepository uar;
    @Override
    public UserAccount createUser(UserAccount ua){
        return uar.save(ua);
    }
    @Override
    public List<UserAccount> getAllUsers(){
        return uar.findAll();
    }
    @Override
    public Optional<UserAccount> getid(long id){
        return uar.findById(id);
    }
   @Override
    public void deactivateUser(long id) {
        Optional<UserAccount> userOpt = uar.findById(id);
        if (userOpt.isPresent()) {
            UserAccount user = userOpt.get();
            user.setActive(false);
            uar.save(user);
        }
    }

    
}
