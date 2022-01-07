package com.example.bookcollection.service

import com.example.bookcollection.data.dto.BookDTO
import com.example.bookcollection.data.entity.Book
import com.example.bookcollection.data.repository.BookRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class BookService(private val bookRepository: BookRepository) {

    fun getAllBooks() = bookRepository.findAll().map { book -> book.toBookDTO() }

    fun getBookById(id: Long) = bookRepository.findById(id).orElseThrow().toBookDTO()

    fun addBook(dto: BookDTO) = bookRepository.save(dto.toBook()).toBookDTO()

    fun updateBook(dto: BookDTO): BookDTO {
        if (dto.id == null) {
            throw IllegalArgumentException("Id can't be null")
        }

        val existing = bookRepository.findById(dto.id).orElseThrow()

        existing.title = dto.title
        existing.author = dto.author
        existing.description = dto.description
        existing.modifiedAt = LocalDateTime.now()

        return bookRepository.save(dto.toBook()).toBookDTO()
    }

    fun deleteBookById(id: Long) = bookRepository.deleteById(id)

}

fun Book.toBookDTO(): BookDTO {
    return BookDTO(this.id, this.title, this.author, this.description)
}

fun BookDTO.toBook(): Book {
    return Book(this.id, this.title, this.author, this.description)
}




