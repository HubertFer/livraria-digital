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
    @Column(unique = true)
    private String userName;
    @Nonnull
    private String password;
    @Nonnull
    private String userStatus;
    private Session session;
    public User(Optional<User> userDto) {
    }

}
