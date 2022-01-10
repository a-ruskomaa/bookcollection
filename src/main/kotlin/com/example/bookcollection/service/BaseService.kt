package com.example.bookcollection.service

interface BaseService<TDTO, K> {
    fun getAll(): List<TDTO>

    fun getOne(id: K): TDTO

    fun add(dto: TDTO): TDTO

    fun update(id: K, dto: TDTO): TDTO

    fun delete(id: K): Unit
}