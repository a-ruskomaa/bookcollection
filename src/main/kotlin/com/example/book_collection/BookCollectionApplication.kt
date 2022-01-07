package com.example.book_collection

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookCollectionApplication

fun main(args: Array<String>) {
	runApplication<BookCollectionApplication>(*args)
}
