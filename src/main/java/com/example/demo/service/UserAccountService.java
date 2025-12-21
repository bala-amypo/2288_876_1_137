// package com.example.demo.service;

// import java.util.List;
// import java.util.Optional;

// import com.example.demo.entity.UserAccount;

// public interface UserAccountService {

//     UserAccount createUser(UserAccount ua);

//     List<UserAccount> getAllUsers();

//     UserAccount getUserById(long id);

//     UserAccount updateUser(long id, UserAccount ua);

//     void deactivateUser(long id);
// }
package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.UserAccount;

public interface UserAccountService {

    // ✅ REQUIRED by controller
    UserAccount createUser(UserAccount user);

    UserAccount updateUser(long id, UserAccount user);

    UserAccount getUserById(long id);

    List<UserAccount> getAllUsers();

    void deactivateUser(long id);
}

