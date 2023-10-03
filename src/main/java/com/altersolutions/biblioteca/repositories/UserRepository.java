package com.altersolutions.biblioteca.repositories;

import com.altersolutions.biblioteca.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByDocument(String document);
    Optional<User> findUserById(Long id);
}
