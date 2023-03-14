package com.wahidabd.library.data

interface BaseRepository {
    abstract val dbService: LocalDb?
    abstract val webService: WebApi?
}