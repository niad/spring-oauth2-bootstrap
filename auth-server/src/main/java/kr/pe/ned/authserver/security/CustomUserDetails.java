package kr.pe.ned.authserver.security;

import kr.pe.ned.authserver.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;

    private Set<GrantedAuthority> authorities;

    public CustomUserDetails(User user, Collection<String> roles) {

        this.username = user.getName();
        this.password = user.getPassword();


        this.authorities = new LinkedHashSet<>();
        // add default role
        this.authorities.add(new SimpleGrantedAuthority("USER"));
        if (roles != null) {
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
