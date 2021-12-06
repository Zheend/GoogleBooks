package com.osipov.googlebooks.domain

import com.osipov.googlebooks.data.local.Book
import com.osipov.googlebooks.data.local.BooksDao
import javax.inject.Inject

class DeleteFromFavoriteUseCase @Inject constructor(
    private val booksDao: BooksDao
) {

    suspend fun delete(book: Book) {
        booksDao.deleteBook(book)
    }
}