package com.example.demo.model;

import lombok.Data;

import com.example.demo.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "wine_users")
public class User implements UserDetails {

    @Id
    private String username;
    private String password;
    private String email;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public User() {
    }

    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        if(role == null)
        {
            this.role = Role.ROLE_USER;
        }
        else
        {
            this.role = role;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    // If you want to provide a list of roles as strings, you can use the following method
    public List<String> getRoles() {
        return Collections.singletonList("ROLE_" + role.name());
    }
}
