package com.example.bookcollection.data.repository

import com.example.bookcollection.data.entity.Book
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
open class BookRepositoryTests(@Autowired private val bookRepository: BookRepository) {
    private val testBooks = listOf(Book(title = "Test title 1", author = "Test author 1", description = ""), Book(title = "Test title 2", author = "Test author 2", description = ""))

    @BeforeEach
    fun prepareDb() {
        bookRepository.deleteAll()
        bookRepository.saveAll(testBooks)
    }

    @Test
    fun findAll_returnsCorrectCount() {
        val allBooks = bookRepository.findAll()

        assertEquals(2, allBooks.count(), "Incorrect book count")
    }

    @Test
    fun findById_returnsCorrectBook() {
        val testBook = bookRepository.save(Book(title = "Test title 3", author = "Test author 3", description = ""));

        assertNotNull(testBook.id)

        val existingBook = bookRepository.findById(testBook.id ?: -1).orElse(null)

        assertEquals(testBook, existingBook, "Books are not equal")
    }
}