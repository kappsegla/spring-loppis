package se.iths.springloppis.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import se.iths.springloppis.entity.RoleEntity;
import se.iths.springloppis.entity.UserEntity;

import java.util.Collection;

public class LoppisUserPrincipal implements UserDetails {

    User user;
    private final UserEntity userEntity;

    public LoppisUserPrincipal(UserEntity userEntity) {
        super();
        this.userEntity = userEntity;
    }

    // Omvandla RoleEntity till något som Spring Security förstår, d.v.s. en SimpleGrantedAuthority
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userEntity.getRoles().stream()
                .map(RoleEntity::getRole)
                .map(String::toUpperCase)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public String getPassword() {
        return this.userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
