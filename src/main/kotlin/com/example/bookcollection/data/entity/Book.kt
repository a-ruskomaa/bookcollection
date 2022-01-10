package com.example.bookcollection.data.entity

import org.hibernate.Hibernate
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
data class Book(
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name = "title", nullable = false, length = 255)
    @field:NotBlank(message = "title must not be blank")
    @field:Size(max = 255, message = "title must be less than 255 characters long")
    var title: String,

    @Column(name = "author", nullable = false, length = 255)
    @field:NotBlank(message = "author must not be blank")
    @field:Size(max = 255, message = "author must be less than 255 characters long")
    var author: String,

    @Column(name = "description", nullable = false, length = 4000)
    @field:Size(max = 4000, message = "description must be less than 4000 characters long")
    var description: String,

    @Column(name = "addedAt", nullable = false)
    var addedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "modifiedAt", nullable = true)
    var modifiedAt: LocalDateTime = LocalDateTime.now()) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Book

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 543153245
}

