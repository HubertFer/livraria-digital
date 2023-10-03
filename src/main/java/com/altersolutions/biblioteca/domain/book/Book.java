package com.altersolutions.biblioteca.domain.book;

import com.altersolutions.biblioteca.domain.user.User;
import com.altersolutions.biblioteca.domain.util.ConstatsUtil;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
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
    @DateTimeFormat(fallbackPatterns = ConstatsUtil.DATE_PATTERN_DEFAULT)
    private LocalDateTime timeStamp;

    public Book(Optional<Book> bookDto) {
    }
    @DateTimeFormat(fallbackPatterns = ConstatsUtil.DATE_PATTERN_DEFAULT)
    private LocalDateTime rentalDate;
    @DateTimeFormat(fallbackPatterns = ConstatsUtil.DATE_PATTERN_DEFAULT)
    private LocalDateTime returningDate;
}
