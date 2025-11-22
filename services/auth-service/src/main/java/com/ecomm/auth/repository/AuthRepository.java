package com.ecomm.auth.repository;

import com.ecomm.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String username);
    Optional<User> findByEmail(String username);
    Optional<User> findByUserNameOrEmail(String username,String email);
}
