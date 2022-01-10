package com.example.bookcollection.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
interface BaseController<T, K> {
    fun getAll(): ResponseEntity<Iterable<T>>

    fun getOne(id: K): ResponseEntity<T>

    fun post(dto: T): ResponseEntity<T>

    fun put(id: K, dto: T): ResponseEntity<T>

    fun delete(id: K): ResponseEntity<Unit>

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(exception: NoSuchElementException): ResponseEntity<Unit> =
        ResponseEntity.notFound().build()

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(exception: IllegalArgumentException): ResponseEntity<Unit> =
        ResponseEntity.badRequest().build()

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<Unit> =
        ResponseEntity.internalServerError().build()
}