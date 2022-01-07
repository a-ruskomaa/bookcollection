package com.example.bookcollection.data.entity

import javax.persistence.*

@Entity
data class Book(
    @Id
    @GeneratedValue
    var id: Long? = null,
    var title: String,
    var author: String,
    var description: String)

