package com.devteria.indentity_service.repository;

import com.devteria.indentity_service.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
        boolean existsByUsername(String username); // kieemr tra su ton tai usernamer
        Optional<User> findByUsername(String username); // tìm uuser name
}
