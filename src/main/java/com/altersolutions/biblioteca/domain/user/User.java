package com.altersolutions.biblioteca.domain.user;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class User {
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
}
