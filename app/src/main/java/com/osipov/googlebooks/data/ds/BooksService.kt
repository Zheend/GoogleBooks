package com.osipov.googlebooks.data.ds

import com.osipov.googlebooks.data.model.BookDto
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface BooksService {

    suspend fun getBooks(query: String): BookDto

    companion object {
        fun create(): BooksService {
            return BooksServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {

                        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                            ignoreUnknownKeys = true
                        })
                    }
                }
            )
        }
    }
}