package com.farmco.farmco_connect.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmco.farmco_connect.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Boolean existsByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
