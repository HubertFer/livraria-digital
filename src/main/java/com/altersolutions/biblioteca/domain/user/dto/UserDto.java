package com.altersolutions.biblioteca.domain.user.dto;

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
    @Column(unique = true)
    private String userName;

    private String passwrod;
    private String userStatus;

}
