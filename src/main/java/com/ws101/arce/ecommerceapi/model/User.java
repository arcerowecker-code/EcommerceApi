package com.ws101.arce.ecommerceapi.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

/**
 * JPA Entity mapping user credentials and authorization roles 
 * to the persistent database schema layer.
 */
@Entity
@Table(name = "users")
public class User implements UserDetails { //
    
    @Id
    private String username; //
    private String password; //
    private String role;     // Stores roles such as "ROLE_USER" or "ROLE_ADMIN"

    // Default Constructor required by JPA
    public User() {}

    // Standard Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    // Spring Security UserDetails Contract Impl Methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Maps the role string to GrantedAuthority
        return List.of(new SimpleGrantedAuthority(this.role)); //
    }

    @Override public boolean isAccountNonExpired() { return true; } //
    @Override public boolean isAccountNonLocked() { return true; } //
    @Override public boolean isCredentialsNonExpired() { return true; } //
    @Override public boolean isEnabled() { return true; } //
}