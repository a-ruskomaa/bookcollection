package com.example.bookcollection.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*

@Controller
interface BaseController<T, K> {
    fun getAll(): ResponseEntity<Iterable<T>>

    fun getOne(id: K): ResponseEntity<T>

    fun post(dto: T): ResponseEntity<T>

    fun put(id: K, dto: T): ResponseEntity<T>

    fun delete(id: K): ResponseEntity<Unit>

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(exception: Exception): ResponseEntity<Any> =
        ResponseEntity.notFound().build()

    @ExceptionHandler(IllegalArgumentException::class, MethodArgumentNotValidException::class)
    fun handleIllegalArgumentException(exception: Exception): ResponseEntity<Any> =
        ResponseEntity.badRequest().build()

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<Any> =
        ResponseEntity.internalServerError().build()
}