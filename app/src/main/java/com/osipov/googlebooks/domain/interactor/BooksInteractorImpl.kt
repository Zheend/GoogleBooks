package com.osipov.googlebooks.domain.interactor

import com.osipov.googlebooks.data.repository.BooksRepository
import com.osipov.googlebooks.domain.model.BookEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BooksInteractorImpl @Inject constructor(
    private val repository: BooksRepository
) : BooksInteractor {

    override fun getFavoriteBooks(): Flow<List<BookEntity>> = repository.getBookFromLocal()

    override suspend fun getBooksByQuery(query: String): List<BookEntity> = withContext(Dispatchers.IO) {
        repository.getBooksByQuery(query)
    }

    override suspend fun addToFavorite(book: BookEntity) = withContext(Dispatchers.IO) {

        repository.addToFavorite(book)
    }

    override suspend fun deleteFavoriteBook(book: BookEntity) = withContext(Dispatchers.IO) {
        repository.deleteBook(book)
    }

    override suspend fun clearFavorite() {
        repository.deleteAllFavoriteBook()
    }
}