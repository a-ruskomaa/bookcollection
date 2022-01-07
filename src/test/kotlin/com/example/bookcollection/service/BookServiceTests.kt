package com.example.bookcollection.service

import com.example.bookcollection.data.dto.BookDTO
import com.example.bookcollection.data.entity.Book
import com.example.bookcollection.data.repository.BookRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.util.*


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookServiceTests {
    @InjectMocks
    private lateinit var service: BookService

    @Mock
    private lateinit var repository: BookRepository

    @BeforeAll
    private fun prepareMock() {
        Mockito.`when`(repository.findAll()).thenReturn(
            listOf(
                Book(id = 1, title = "Test title 1", author = "Test author 1", description = ""),
                Book(id = 2, title = "Test title 2", author = "Test author 2", description = "")
            ))

        Mockito.`when`(repository.findById(1)).thenReturn(
            Optional.of(Book(id = 1, title = "Test title 1", author = "Test author 1", description = "")
            ))
    }

    @Test
    fun getAllBooks_ReturnsCorrectCount() {
        val books: List<BookDTO> = service.getAllBooks()
        assertEquals(2, books.size)
    }


    @Test
    fun getBookById_ReturnsCorrectBook() {
        val book: BookDTO = service.getBookById(1)
        assertEquals(1, book.id)
        assertEquals("Test title 1", book.title)
        assertEquals("Test author 1", book.author)
        assertEquals("", book.description)
    }
}