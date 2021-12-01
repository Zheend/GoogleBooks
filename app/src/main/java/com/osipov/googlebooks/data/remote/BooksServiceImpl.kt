package com.osipov.googlebooks.data.remote

import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class BooksServiceImpl @Inject constructor(
    private val client: HttpClient
) : BooksService {
    override suspend fun getBooks(query: String): BooksResponse {
        return client.get {
            url(HttpRoutes.BOOKS)
            parameter("q", "$query+$SEARCH_TYPE")
//            parameter("key", "AIzaSyAXFfEU6Veq9aDTv8q9jdJaxKOaP5aqcbE")
        }
    }
}

private const val SEARCH_TYPE = "intitle"