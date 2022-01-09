package com.example.bookcollection.controller

import com.example.bookcollection.data.dto.BookDTO
import com.example.bookcollection.data.entity.Book
import com.example.bookcollection.service.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException

@RestController
@RequestMapping("api/books")
class BookController(private val bookService: BookService) {

    @GetMapping()
    fun getBooks(): ResponseEntity<Iterable<BookDTO>> = ResponseEntity.ok(bookService.getAllBooks())

    @GetMapping("/{bookId}")
    fun getBook(@PathVariable bookId: Long): ResponseEntity<BookDTO> {
        return try {
            ResponseEntity.ok(bookService.getBookById(bookId))
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping()
    fun postBook(@RequestBody dto: BookDTO): BookDTO = bookService.addBook(dto)

    @PutMapping("/{bookId}")
    fun putBook(@PathVariable bookId: Long, @RequestBody dto: BookDTO): ResponseEntity<BookDTO> {
        return try {
            ResponseEntity.ok(bookService.updateBook(bookId, dto))
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
    }

    @DeleteMapping("/{bookId}")
    fun deleteBook(@PathVariable bookId: Long): ResponseEntity<Unit> {
        return try {
            bookService.deleteBookById(bookId)
            ResponseEntity.noContent().build()
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }

    }
}
