package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.UserAccount;

public interface UserAccountService {

    UserAccount createUser(UserAccount userAccount);

    List<UserAccount> getAllUsers();

    UserAccount getUserById(Long id);

    UserAccount updateUser(Long id, UserAccount userAccount);

    UserAccount deactivateUser(Long id);
}














// package com.example.demo.service;

// import java.util.List;
// import com.example.demo.entity.UserAccount;

// public interface UserAccountService {

//     // ✅ REQUIRED by controller
//     UserAccount createUser(UserAccount user);

//     UserAccount updateUser(long id, UserAccount user);

//     UserAccount getUserById(long id);

//     List<UserAccount> getAllUsers();

//     void deactivateUser(long id);
// }
