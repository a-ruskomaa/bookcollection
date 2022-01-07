package com.example.bookcollection.controller

import com.example.bookcollection.data.entity.Book
import com.example.bookcollection.service.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("books")
class BookController(private val bookService: BookService) {

    @GetMapping("/")
    fun getBooks(): Iterable<Book> = bookService.getAllBooks()
}
