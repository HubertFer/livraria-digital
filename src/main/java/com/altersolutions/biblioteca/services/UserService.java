package com.altersolutions.biblioteca.services;

import com.altersolutions.biblioteca.domain.user.Session;
import com.altersolutions.biblioteca.domain.user.User;
import com.altersolutions.biblioteca.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAllUsers(){
        List<User> users = repository.findAll();
        return users;
    }

    public User findByUserName(User user){
        return repository.findByUserName(user);
    }
    public User findUserById (Long id) throws Exception {
        return this.repository.findUserById(id).
                orElseThrow(() -> new Exception( "Usuário não encontrado"));
    }

    public void createUser(User user){
        this.repository.save(user);
    }
    public User updateUserById (Long id) throws Exception {
        Optional<User> userDto = Optional.ofNullable(repository.findUserById(id).
                orElseThrow(() -> new Exception("Usuário não encontrado")));
        Optional<User> user = userDto;
        if (user.get().getSession() == Session.ADMIN){

            return this.repository.save(new User(userDto));
        }
        return null;
    }

}
