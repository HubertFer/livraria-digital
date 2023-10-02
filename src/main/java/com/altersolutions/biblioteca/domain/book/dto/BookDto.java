package com.altersolutions.biblioteca.domain.book.dto;

import com.altersolutions.biblioteca.domain.user.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class BookDto {
    private String bookName;
    private String authorName;
    private String publisher;
    private User user;
    private LocalDateTime timeStamp;
}
