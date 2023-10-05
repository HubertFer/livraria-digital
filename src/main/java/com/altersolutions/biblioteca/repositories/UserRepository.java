package com.altersolutions.biblioteca.repositories;

import com.altersolutions.biblioteca.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(User user);
    Optional<User> findUserById(Long id);
}
