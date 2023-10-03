package com.altersolutions.biblioteca.domain.user;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nonnull
    private String firstName;
    @Nonnull
    private String lastName;
    @Nonnull
    @Column(unique = true)
    private String document;
    @Nonnull
    private String password;
    @Nonnull
    @Column(unique = true)
    private String email;
    @Nonnull
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Nonnull
    private String userStatus;
    public User(Optional<User> userDto) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userType == UserType.ADMIN)
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_COMMON"));
        else return List.of(new SimpleGrantedAuthority("ROLE_COMMON"));
    }

    @Override
    public String getUsername() {
        return document;
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
