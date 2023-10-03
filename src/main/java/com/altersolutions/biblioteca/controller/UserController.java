package com.altersolutions.biblioteca.controller;

import com.altersolutions.biblioteca.domain.user.User;
import com.altersolutions.biblioteca.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/user")
    public ModelAndView listAll(){
        ModelAndView mv = new ModelAndView("user");
        mv.addObject("user", new User());
        return mv;
    }

    @PostMapping("/create")
    public String create(User user){
       log.info(String.format("User created - document[%s]", user.getDocument()));
       userService.createUser(user);
       return "redirect:/usersList";
    }

    @GetMapping("/users-list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("usersList");
        List<User> list = userService.findAllUsers();
        mv.addObject("list", list);
        return mv;
    }
}
