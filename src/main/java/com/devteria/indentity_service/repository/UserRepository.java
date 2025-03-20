package com.devteria.indentity_service.repository;

import com.devteria.indentity_service.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
        boolean existsByUsername(String username); // kieemr tra su ton tai usernamer
}
