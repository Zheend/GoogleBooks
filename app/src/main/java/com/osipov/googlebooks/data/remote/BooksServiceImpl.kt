package com.osipov.googlebooks.data.remote

import io.ktor.client.*
import io.ktor.client.request.*

class BooksServiceImpl constructor(
    private val client: HttpClient
) : BooksService {
    override suspend fun getBooks(): List<BooksResponse> {
        return client.get { url(HttpRoutes.BOOKS) }
    }
}