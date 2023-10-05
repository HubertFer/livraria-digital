package com.altersolutions.biblioteca.controller;

import com.altersolutions.biblioteca.domain.book.Book;
import com.altersolutions.biblioteca.domain.util.ConstatsUtil;
import com.altersolutions.biblioteca.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
       book.setBookAvailable(true);
       bookservice.saveBook(book);
       return "redirect:/booksList";
    }

    @GetMapping("/booksList")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("booksList");
        List<Book> list = bookservice.findAllBooks();
        mv.addObject("list", list);
        return mv;
    }

    @GetMapping("/rent/{id}")
    public String rent(@PathVariable("id") Long id) throws Exception {
        Book findBook = bookservice.findBookById(id);
        findBook.setReturningDate(LocalDateTime.now().plusDays(ConstatsUtil.DEFAULT_RENT_PERIOD));
        findBook.setRentalDate(LocalDateTime.now());
        record(findBook);
        return "redirect:/booksList";
    }
    @GetMapping("/return/{id}")
    public String returnBook (@PathVariable("id") Long id) throws Exception {
        Book findBook = bookservice.findBookById(id);
        findBook.setReturningDate(LocalDateTime.now());
        record(findBook);
        return "redirect:/booksList";
    }
}
