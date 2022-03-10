package se.iths.springloppis.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import se.iths.springloppis.entity.RoleEntity;
import se.iths.springloppis.entity.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LoppisUserPrincipal implements UserDetails {

    private final UserEntity userEntity;

    public LoppisUserPrincipal(UserEntity userEntity) {
        super();
        this.userEntity = userEntity;
    }

    // Omvandla RoleEntity till något som Spring Security förstår, d.v.s. en SimpleGrantedAuthority
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<RoleEntity> roles = userEntity.getRoles();
        return userEntity.getRoles()
                .stream().map(authority -> new SimpleGrantedAuthority(authority.getRole())).collect(Collectors.toList());
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
