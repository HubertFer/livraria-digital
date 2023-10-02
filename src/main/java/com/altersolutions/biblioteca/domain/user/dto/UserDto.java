package com.altersolutions.biblioteca.domain.user.dto;

import com.altersolutions.biblioteca.domain.user.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String userStatus;
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
