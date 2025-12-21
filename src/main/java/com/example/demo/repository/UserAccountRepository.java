package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    // ✅ REQUIRED FOR LOGIN
    Optional<UserAccount> findByEmail(String email);
}
