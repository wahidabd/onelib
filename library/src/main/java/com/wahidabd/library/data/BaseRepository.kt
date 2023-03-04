package com.wahidabd.library.data

interface BaseRepository {
    abstract val dbService: LocalDB
    abstract val webService: WebApi
}