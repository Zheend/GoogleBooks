package com.osipov.googlebooks.data.ds

import com.osipov.googlebooks.Database
import com.osipov.googlebooks.domain.model.BookEntity
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import comosipovgooglebooks.entity.BookTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BooksDao @Inject constructor(
    private val sqlDriver: SqlDriver
) : CoroutineScope by CoroutineScope(Dispatchers.IO) {

    private val bookEntityQuery = Database(sqlDriver).bookItemQueries

    val allBooks: Flow<List<BookTable>>
        get() = bookEntityQuery
            .selectAll()
            .asFlow()
            .map { query ->
                query.executeAsList()
            }

    suspend fun upsert(book: BookEntity) {
        bookEntityQuery.insertOrReplace(
            title = book.title,
            image = book.image,
            author = book.author,
            link = book.link
        )
    }

    suspend fun selectByTitle(book: BookEntity) : Boolean {
        val result = bookEntityQuery.selectByTitle(book.title).executeAsOneOrNull()
        return result != null
    }

    suspend fun deleteAll() {
        bookEntityQuery.empty()
    }

    suspend fun deleteBook(book: BookEntity) {
        bookEntityQuery.deleteByLabel(book.title)
    }

}
