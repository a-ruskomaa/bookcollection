package com.example.bookcollection.data.repository

import com.example.bookcollection.data.entity.Book
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class BookRepositoryTests(@Autowired private val bookRepository: BookRepository) {
    private val testBooks = listOf(Book(title = "Test title 1", author = "Test author 1", description = ""), Book(title = "Test title 2", author = "Test author 2", description = ""))

    @BeforeAll
    fun prepareDb() {
        bookRepository.saveAll(testBooks)
    }

    @Test
    fun findAllBooks_returnsCorrectCount() {
        val allBooks = bookRepository.findAll()

        assertEquals(allBooks.count(), 2, "Incorrect book count")
    }
}