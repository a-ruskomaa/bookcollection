package com.example.bookcollection.service

import com.example.bookcollection.data.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository) {

    fun getAllBooks() = bookRepository.findAll()
}