package com.altersolutions.biblioteca.controller;

import com.altersolutions.biblioteca.domain.book.Book;
import com.altersolutions.biblioteca.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class BookController {

    @Autowired
    private BookService bookservice;
    @GetMapping("/book")
    public ModelAndView listAll(){
        ModelAndView mv = new ModelAndView("book");
        mv.addObject("book", new Book());
        return mv;
    }

    @PostMapping("/record")
    public String record(Book book){
       log.info(String.format("Livro recorded - [%s]", book.getBookName()));
        bookservice.saveBook(book);
       return "redirect:/booksList";
    }

    @GetMapping("/books-list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("booksList");
        List<Book> list = bookservice.findAllBooks();
        mv.addObject("list", list);
        return mv;
    }

    @PostMapping("/rent")
    public String rent(){

        return null;
    }
}
