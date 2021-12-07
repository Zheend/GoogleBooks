package com.osipov.googlebooks.domain.interactor

import com.osipov.googlebooks.domain.model.BookEntity
import kotlinx.coroutines.flow.Flow

interface BooksInteractor {

    fun getFavoriteBooks(): Flow<List<BookEntity>>

    suspend fun getBooksByQuery(query: String): List<BookEntity>

    suspend fun addToFavorite(book: BookEntity)

    suspend fun deleteFavoriteBook(book: BookEntity)

    suspend fun clearFavorite()

}