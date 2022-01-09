package com.example.bookcollection.service

import com.example.bookcollection.data.dto.BookDTO
import com.example.bookcollection.data.entity.Book
import com.example.bookcollection.data.repository.BookRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class BookService(private val bookRepository: BookRepository) {

    fun getAllBooks() = bookRepository.findAll().map { book -> book.toBookDTO() }

    fun getBookById(id: Long) = bookRepository.findById(id).orElseThrow().toBookDTO()

    fun addBook(dto: BookDTO) = bookRepository.save(dto.toBook()).toBookDTO()

    fun updateBook(bookId: Long, dto: BookDTO): BookDTO {
        if (dto.id != bookId) {
            throw IllegalArgumentException("Id's must match")
        }

        val existing = bookRepository.findById(bookId).orElseThrow()

        existing.title = dto.title
        existing.author = dto.author
        existing.description = dto.description
        existing.modifiedAt = LocalDateTime.now()

        return bookRepository.save(existing).toBookDTO()
    }

    fun deleteBookById(id: Long) {
        try {
            bookRepository.deleteById(id)
        } catch (e: EmptyResultDataAccessException)
        {
            throw NoSuchElementException("Id not found")
        }
    }

}

fun Book.toBookDTO(): BookDTO {
    return BookDTO(this.id, this.title, this.author, this.description)
}

fun BookDTO.toBook(): Book {
    return Book(this.id, this.title, this.author, this.description)
}




