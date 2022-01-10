package com.example.bookcollection.controller

import com.example.bookcollection.data.dto.BookDTO
import com.example.bookcollection.service.BookService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/books")
class BookController(private val bookService: BookService) : BaseController<BookDTO, Long> {

    @GetMapping()
    override fun getAll(): ResponseEntity<Iterable<BookDTO>> =
        ResponseEntity.ok(bookService.getAll())

    @GetMapping("/{id}")
    override fun getOne(@PathVariable id: Long): ResponseEntity<BookDTO> =
        ResponseEntity.ok(bookService.getOne(id))

    @PostMapping()
    override fun post(@RequestBody @Validated dto: BookDTO): ResponseEntity<BookDTO> =
        ResponseEntity.ok(bookService.add(dto))

    @PutMapping("/{id}")
    override fun put(@PathVariable id: Long, @RequestBody @Validated dto: BookDTO): ResponseEntity<BookDTO> =
        ResponseEntity.ok(bookService.update(id, dto))

    @DeleteMapping("/{id}")
    override fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        bookService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
