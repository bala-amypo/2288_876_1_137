
package com.example.demo.serveice;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.UserAccount;

public interface UserAccountService {

    public UserAccount createuser(UserAccount ua);
    public List<UserAccount> getall();
    public Optional<UserAccount> getid(long id);
    
}
