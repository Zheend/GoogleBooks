package com.osipov.googlebooks.data.model

import com.osipov.googlebooks.Database
import com.osipov.googlebooks.domain.model.BookEntity
import com.osipov.googlebooks.utils.toBookList
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BooksDao @Inject constructor(
    private val sqlDriver: SqlDriver
) : CoroutineScope by CoroutineScope(Dispatchers.IO) {

    private val bookEntityQuery = Database(sqlDriver).bookItemQueries

    val allBooks: Flow<List<BookEntity>>
        get() = bookEntityQuery
            .selectAll()
            .asFlow()
            .map { query ->
                query.executeAsList().toBookList()
            }.flowOn(Dispatchers.IO)

    suspend fun upsert(book: BookEntity) {
        bookEntityQuery.insertOrReplace(
            title = book.title,
            image = book.image,
            author = book.author,
            link = book.link
        )
    }

    suspend fun deleteAll() {
        bookEntityQuery.empty()
    }

    suspend fun deleteBook(book: BookEntity) {
        bookEntityQuery.deleteByLabel(book.title)
    }

}
