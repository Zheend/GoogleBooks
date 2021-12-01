package com.osipov.googlebooks.domain

import com.osipov.googlebooks.data.remote.BooksResponse
import com.osipov.googlebooks.data.remote.BooksService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchBooksUseCase @Inject constructor(
    private val booksService: BooksService
) {

    suspend fun searchBooks(query: String): BooksResponse = withContext(Dispatchers.IO) {
        booksService.getBooks(query = query)
    }

}