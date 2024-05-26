package com.wahidabd.library.data

interface BaseRepository {
    val dbService: LocalDb?
    val webService: WebApi?
}