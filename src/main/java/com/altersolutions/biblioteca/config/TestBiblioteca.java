package com.altersolutions.biblioteca.config;

import com.altersolutions.biblioteca.domain.book.Book;
import com.altersolutions.biblioteca.domain.user.User;
import com.altersolutions.biblioteca.domain.user.UserType;
import com.altersolutions.biblioteca.repositories.BookRepository;
import com.altersolutions.biblioteca.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestBiblioteca implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Override
    public void run(String... args) throws Exception {
        User admin = new User(
                null, "John", "Brown", "123456789"
                ,"123456", "john@gmail.com", UserType.ADMIN,"Ativo");
        User common = new User(
                null, "Isaac", "Newton", "234567890"
                ,"123456", "isaac@gmail.com", UserType.COMMON,"Ativo");
        LocalDateTime hoje = LocalDateTime.now();
        LocalDateTime ontem = LocalDateTime.now().minusDays(1);
        Book book1 = new Book(
                null, "Forrest Gump", "Winston Groom",
                "Editora Aleph", false, common, hoje, hoje, null);
        Book book2 = new Book(
                null, "O Senhor dos Anéis: As duas torres", "J.R.R Tolkien",
                "Harper Collins", true, admin, ontem, ontem, hoje);

        userRepository.saveAll(Arrays.asList(admin, common));
        bookRepository.saveAll(Arrays.asList(book1, book2));
    }
}
