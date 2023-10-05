package com.altersolutions.biblioteca.controller;

import com.altersolutions.biblioteca.domain.book.Book;
import com.altersolutions.biblioteca.domain.user.Session;
import com.altersolutions.biblioteca.domain.user.User;
import com.altersolutions.biblioteca.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String loginUser(){
        return "login";
    }

    @PostMapping("/login")
    public String login(User user) throws Exception {
            //TODO: Implements OATH2 or JTW - This is only a dumb UNSECURE login method
            if(user.getUserName().equalsIgnoreCase("admin")
                && user.getPassword().equalsIgnoreCase("123")){
                return "redirect:/usersList";
            }
            if (user.getUserName().equalsIgnoreCase("common")
                    && user.getPassword().equalsIgnoreCase("123")){
                return "redirect:/booksList";
            }
            else{
                return "/erroLogin";
            }
    }

    @GetMapping("/user")
    public ModelAndView listAll(){
        ModelAndView mv = new ModelAndView("user");
        mv.addObject("user", new User());
        return mv;
    }

    @PostMapping("/create")
    public String create(User user){
       log.info(String.format("User created - userName[%s]", user.getUserName()));
       userService.createUser(user);
       return "redirect:/usersList";
    }

    @GetMapping("/usersList")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("usersList");
        List<User> list = userService.findAllUsers();
        mv.addObject("list", list);
        return mv;
    }
    @GetMapping("/disable/{id}")
    public String disableUser (@PathVariable("id") Long id) throws Exception {
        User findUser = userService.findUserById(id);
        findUser.setUserStatus("inativo");
        create(findUser);
        return "redirect:/usersList";
    }
    @GetMapping("/enable/{id}")
    public String enaableUser (@PathVariable("id") Long id) throws Exception {
        User findUser = userService.findUserById(id);
        findUser.setUserStatus("ativo");
        create(findUser);
        return "redirect:/usersList";
    }
}
