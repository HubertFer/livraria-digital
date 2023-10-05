package com.altersolutions.biblioteca.config;

import com.altersolutions.biblioteca.domain.book.Book;
import com.altersolutions.biblioteca.domain.user.Session;
import com.altersolutions.biblioteca.domain.user.User;
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
        User admin = new User(null,"admin","123", "ativo", Session.ADMIN);
        User common = new User(null, "common", "123", "ativo",Session.COMMON);

        LocalDateTime hoje = LocalDateTime.now();
        LocalDateTime ontem = LocalDateTime.now().minusDays(1);
        Book book1 = new Book(
                null, "Forrest Gump", "Winston Groom",
                "Editora Aleph", false, common, hoje, hoje, hoje);
        Book book2 = new Book(
                null, "O Senhor dos Anéis: As duas torres", "J.R.R Tolkien",
                "Harper Collins", true, admin, ontem, ontem, hoje);
        Book book3 = new Book(
                null, "O Livro dos cinco anéis", "Myamoto Musashi",
                "Editara jardim japonês", true, admin, ontem, ontem, hoje);
        Book book4 = new Book(
                null, "Harry Potter e a Pedra Filosofal: 1", "J.K. Rowling",
                "Editora", true, admin, ontem, ontem, hoje);
        Book book5 = new Book(
                null, "A Guerra dos Tronos : As Crônicas de Gelo e Fogo, volume 1"
                , "George R. R. Martin ","Suma", true, common, ontem, ontem, hoje);
        Book book6 = new Book(
                null, "A guerra dos mundos", "H.G. Wells",
                "Suma", true, admin, ontem, ontem, hoje);
        Book book7 = new Book(
                null, "Os Deuses eram astronautas", "Erich Von Daniken",
                "Madras", true, admin, ontem, ontem, hoje);

        userRepository.saveAll(Arrays.asList(admin, common));
        bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4, book5, book6, book7));
    }
}
