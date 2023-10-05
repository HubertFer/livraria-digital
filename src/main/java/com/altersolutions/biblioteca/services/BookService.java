package com.altersolutions.biblioteca.services;

import com.altersolutions.biblioteca.domain.book.Book;
import com.altersolutions.biblioteca.domain.user.Session;
import com.altersolutions.biblioteca.domain.user.User;
import com.altersolutions.biblioteca.domain.util.ConstatsUtil;
import com.altersolutions.biblioteca.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public List<Book> findAllBooks(){
        List<Book> books = repository.findAll();
        return books;
    }

    //TODO better exception treatment
    public Book findBookById (Long id) throws Exception{

        return this.repository.findBookById(id).
                orElseThrow(() -> new Exception( "Livro não encontrado"));
    }

    public boolean isBookAvailableForRent(Optional<Book> book){
        LocalDateTime date = book.get().getTimeStamp();
        boolean isAvailable;
        if (book.get().getReturningDate().isBefore(date)){
            isAvailable = true;
        }
        else if (book.get().getReturningDate() == null) {
            isAvailable = true;
        }
        else {
            isAvailable = false;
        }
        return isAvailable;
    }
    public void saveBook(Book book){
        LocalDateTime date = LocalDateTime.now();
        book.setTimeStamp(date);
        book.setBookAvailable(true);
        this.repository.save(book);
    }
    public Book updateBookById (Long id) throws Exception {
        Optional<Book> BookDto = Optional.ofNullable((Book) repository.findBookById(id).
                orElseThrow(() -> new Exception("Livro não encontrado")));
        return this.repository.save(new Book(BookDto));
    }

    public void deleteAllBooks (){
        repository.deleteAll();
    }

    public void deleteBookById(Long id){
        repository.deleteById(id);
    }

    public Optional<Book> rentABook(Long id, User user) throws Exception {
        if (user.getSession() == Session.ADMIN || user.getSession() == Session.COMMON){
            Optional<Book> book = Optional.of(new Book());
            book = Optional.ofNullable(repository.findBookById(id).
                    orElseThrow(() -> new Exception("Livro não encontrado")));
            book.get().setBookAvailable(isBookAvailableForRent(book));
            LocalDateTime rentDate = book.get().getTimeStamp();
            LocalDateTime returningDate = rentDate.plusDays(ConstatsUtil.DEFAULT_RENT_PERIOD);
            book.get().setReturningDate(returningDate);
            return book;
        }
        //TODO: get this "return" better
        return null;
    }
    public Optional<Book> returnABook(Long id) throws Exception {
        Optional<Book> book = Optional.of(new Book());
        book = Optional.ofNullable(repository.findBookById(id).
                orElseThrow(() -> new Exception("Livro não encontrado")));
        LocalDateTime returningDate = book.get().getTimeStamp();
        book.get().setReturningDate(returningDate);
        return book;
    }

}
