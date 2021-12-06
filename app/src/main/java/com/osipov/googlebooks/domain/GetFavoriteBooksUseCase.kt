package com.osipov.googlebooks.domain

import com.osipov.googlebooks.data.local.Book
import com.osipov.googlebooks.data.local.BooksDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteBooksUseCase @Inject constructor(
    private val booksDao: BooksDao
) {

    fun getFavoriteBooks(): Flow<List<Book>> = booksDao.allBooks

    suspend fun clearFavorite() = booksDao.deleteAll()

}