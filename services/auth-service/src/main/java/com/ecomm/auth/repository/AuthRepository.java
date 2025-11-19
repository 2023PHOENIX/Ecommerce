package com.ecomm.auth.repository;

import com.ecomm.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthRepository extends JpaRepository<User,Long> {
}
