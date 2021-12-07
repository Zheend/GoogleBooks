package com.osipov.googlebooks.data.repository

import com.osipov.googlebooks.data.ds.BooksService
import com.osipov.googlebooks.data.mapper.BooksDtoToEntityMapper
import com.osipov.googlebooks.data.model.BooksDao
import com.osipov.googlebooks.domain.model.BookEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val booksService: BooksService,
    private val booksDao: BooksDao,
    private val booksDtoToEntityMapper: BooksDtoToEntityMapper
) {

    fun getBookFromLocal(): Flow<List<BookEntity>> = booksDao.allBooks

    suspend fun getBooksByQuery(query: String): List<BookEntity> =
        booksDtoToEntityMapper.mapBookEntityList(booksService.getBooks(query))

    suspend fun addToFavorite(book: BookEntity) = booksDao.upsert(book)

    suspend fun deleteBook(book: BookEntity) = booksDao.deleteBook(book)

    suspend fun deleteAllFavoriteBook() = booksDao.deleteAll()

}