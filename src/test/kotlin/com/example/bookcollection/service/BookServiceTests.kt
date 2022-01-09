package com.example.bookcollection.service

import com.example.bookcollection.data.dto.BookDTO
import com.example.bookcollection.data.entity.Book
import com.example.bookcollection.data.repository.BookRepository
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.AdditionalAnswers.returnsFirstArg
import org.mockito.AdditionalMatchers.not
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.EmptyResultDataAccessException
import java.time.LocalDateTime
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
                Book(id = 1, title = "Test title 1", author = "Test author 1", description = "", addedAt = LocalDateTime.now().minusMinutes(10)),
                Book(id = 2, title = "Test title 2", author = "Test author 2", description = "", addedAt = LocalDateTime.now().minusMinutes(10))
            ))

        Mockito.`when`(repository.findById(1)).thenAnswer {
            Optional.of(Book(id = 1, title = "Test title 1", author = "Test author 1", description = "", addedAt = LocalDateTime.now().minusMinutes(10)))
        }

        Mockito.`when`(repository.save(any())).thenAnswer(returnsFirstArg<Book>())

        Mockito.`when`(repository.deleteById(not(eq(1L)))).thenThrow(EmptyResultDataAccessException(1))

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

    @Test
    fun updateBook_UpdatesCorrectProperties() {
        val book = BookDTO(id = 1, title = "Edited title", author = "Edited author", description = "This here is completely new")

        val savedBook = service.updateBook(1, book)

        assertEquals(book.id, savedBook.id)
        assertEquals(book.title, savedBook.title)
        assertEquals(book.author, savedBook.author)
        assertEquals(book.description, savedBook.description)
    }

    @Test
    fun updateBook_ThrowsWhenWrongId() {
        val book = BookDTO(id = 2, title = "Test title 2", author = "Test author 2", description = "This here is new")

        assertThrows<IllegalArgumentException> { service.updateBook(1, book) }
    }

    @Test
    fun deleteBookById_ReturnsWhenCorrectId() {
        assertDoesNotThrow { service.deleteBookById(1) }
    }

    @Test
    fun deleteBookById_ThrowsWhenWrongId() {
        assertThrows<NoSuchElementException> { service.deleteBookById(2) }
    }
}