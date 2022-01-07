package com.example.bookcollection.controller

import com.example.bookcollection.data.dto.BookDTO
import com.example.bookcollection.data.entity.Book
import com.example.bookcollection.service.BookService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("books")
class BookController(private val bookService: BookService) {

    @GetMapping("/")
    fun getBooks(): Iterable<BookDTO> = bookService.getAllBooks()

    @GetMapping("/{bookId}")
    fun getBook(@PathVariable bookId: Long): BookDTO = bookService.getBookById(bookId)

    @PostMapping("/")
    fun postBook(dto: BookDTO): BookDTO = bookService.addBook(dto)

    @PutMapping("/{bookId}")
    fun putBook(@PathVariable bookId: Long, dto: BookDTO): BookDTO = bookService.updateBook(dto)

    @DeleteMapping("/{bookId}")
    fun deleteBook(@PathVariable bookId: Long) = bookService.deleteBookById(bookId)
}
