package com.altersolutions.biblioteca.domain.book;

import com.altersolutions.biblioteca.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Entity(name="books")
@Table(name="books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookName;
    private String authorName;
    private String publisher;
    private boolean isBookAvailable;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime timeStamp;

    public Book(Optional<Book> bookDto) {
    }

    private LocalDateTime rentDate;
    private LocalDateTime returningDate;
}
