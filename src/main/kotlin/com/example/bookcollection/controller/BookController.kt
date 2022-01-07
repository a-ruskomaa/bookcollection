package com.example.bookcollection.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("books")
class BookController {

    @GetMapping("/")
    fun getBooks(): String {
        return "Hello books"
    }
}
