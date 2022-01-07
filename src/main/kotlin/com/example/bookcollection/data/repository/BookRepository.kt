package com.example.bookcollection.data.repository

import com.example.bookcollection.data.entity.Book
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, Long> {
}