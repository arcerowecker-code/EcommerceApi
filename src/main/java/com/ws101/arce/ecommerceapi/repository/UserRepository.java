package com.ws101.arce.ecommerceapi.repository;

import com.ws101.arce.ecommerceapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // String represents the primary key ID type mapping to your username field
}