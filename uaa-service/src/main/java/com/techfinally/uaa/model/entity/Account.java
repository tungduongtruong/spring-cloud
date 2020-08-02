package com.techfinally.uaa.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author Truong Duong
 */
@Getter
@Setter
@Entity
@Table(name = "account")
public class Account extends BaseIdEntity implements Serializable, UserDetails {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "account_default")
    private boolean accountDefault;

    @Column(name = "account_locked")
    private boolean accountNonLocked;

    @Column(name = "account_expired")
    private boolean accountNonExpired;

    @Column(name = "credentials_expired")
    private boolean credentialsNonExpired;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AccountRole> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getRole().getName()));
            r.getRole().getPermissions().forEach(p -> {
                authorities.add(new SimpleGrantedAuthority(p.getPermission().getName()));
            });
        });
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountNonExpired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountNonLocked;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

}
