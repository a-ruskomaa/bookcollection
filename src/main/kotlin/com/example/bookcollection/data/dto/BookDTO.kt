package com.example.bookcollection.data.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class BookDTO(
        val id: Long? = null,

        @field:NotBlank(message = "title must not be blank")
        @field:Size(max = 255, message = "title must be less than 255 characters long")
        val title: String,

        @field:NotBlank(message = "author must not be blank")
        @field:Size(max = 255, message = "author must be less than 255 characters long")
        val author: String,

        @field:Size(max = 4000, message = "description must be less than 4000 characters long")
        val description: String)