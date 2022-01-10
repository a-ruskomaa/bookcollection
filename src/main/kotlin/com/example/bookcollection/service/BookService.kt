package com.example.bookcollection.service

import com.example.bookcollection.data.dto.BookDTO
import com.example.bookcollection.data.entity.Book
import com.example.bookcollection.data.repository.BookRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class BookService(private val bookRepository: BookRepository) : BaseService<BookDTO, Long> {

    override fun getAll(): List<BookDTO> = bookRepository.findAll().map { book -> book.toBookDTO() }

    override fun getOne(id: Long): BookDTO = bookRepository.findById(id).orElseThrow().toBookDTO()

    override fun add(dto: BookDTO): BookDTO = bookRepository.save(dto.toBook()).toBookDTO()

    override fun update(id: Long, dto: BookDTO): BookDTO {
        if (dto.id != id) {
            throw IllegalArgumentException("Id's must match")
        }

        val existing = bookRepository.findById(id).orElseThrow()

        existing.title = dto.title
        existing.author = dto.author
        existing.description = dto.description
        existing.modifiedAt = LocalDateTime.now()

        return bookRepository.save(existing).toBookDTO()
    }

    override fun delete(id: Long): Unit = bookRepository.delete(bookRepository.findById(id).orElseThrow())
}

fun Book.toBookDTO(): BookDTO = BookDTO(this.id, this.title, this.author, this.description)

fun BookDTO.toBook(): Book = Book(this.id, this.title, this.author, this.description)
