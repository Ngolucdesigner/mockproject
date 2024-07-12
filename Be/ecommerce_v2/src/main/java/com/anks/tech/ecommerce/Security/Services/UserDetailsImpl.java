package com.anks.tech.ecommerce.Security.Services;

import com.anks.tech.ecommerce.Entity.Account;
import com.anks.tech.ecommerce.Entity.FileProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private int id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;
    private Boolean isActive;

    private FileProduct avatar;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(int id, String username, String email, String password, Boolean isActive, FileProduct avatar,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
        this.avatar = avatar;
    }

    public static UserDetailsImpl build(Account user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add( new SimpleGrantedAuthority(user.getRole().name()));

        if(user.getFileProduct()==null){
            return new UserDetailsImpl(
                    user.getAccountId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getActive(),
                    null,
                    authorities);
        }
        else {
            return new UserDetailsImpl(
                    user.getAccountId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getActive(),
                    user.getFileProduct(),
                    authorities);
        }


    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username ;
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
        return isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
