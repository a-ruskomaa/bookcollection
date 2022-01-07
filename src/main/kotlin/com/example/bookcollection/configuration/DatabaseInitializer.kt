package com.example.bookcollection.configuration

import com.example.bookcollection.data.entity.Book
import com.example.bookcollection.data.repository.BookRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class DatabaseInitializer {

    @Bean
    open fun initializeDb(bookRepository: BookRepository) = ApplicationRunner {

        bookRepository.save(Book(
            author = "Terry Pratchett",
            title = "Reaper man",
            description = "Lorem ipsum"
        ))

        bookRepository.save(Book(
            author = "Kernighan - Ritchie",
            title = "The C programming language",
            description = ""
        ))
    }
}