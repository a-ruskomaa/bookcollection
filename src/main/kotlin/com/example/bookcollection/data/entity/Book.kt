package com.example.bookcollection.data.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Book(
    @Id
    @GeneratedValue
    var id: Long? = null,
    var title: String,
    var author: String,
    var addedAt: LocalDateTime = LocalDateTime.now(),
    var description: String)
