package com.osipov.googlebooks.data.local

import com.osipov.googlebooks.Database
import com.osipov.googlebooks.data.remote.Item
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import comosipovgooglebooks.entity.BookTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


data class Book(
    val title: String,
    val image: String,
    val link: String,
    val isFavorite: Boolean = false
)

fun BookTable.toBookItem() = Book(
    title = title,
    image = image,
    link = link.orEmpty()
)

fun List<BookTable>.toBookList(): List<Book> = map {
    it.toBookItem()
}

@Singleton
class BooksDao @Inject constructor(
    private val sqlDriver: SqlDriver
) : CoroutineScope by CoroutineScope(Dispatchers.IO) {

    private val bookEntityQuery = Database(sqlDriver).bookItemQueries

    val allBooks: Flow<List<Book>>
        get() = bookEntityQuery
            .selectAll()
            .asFlow()
            .map { query ->
                query.executeAsList().toBookList()
            }.flowOn(Dispatchers.IO)

    suspend fun upsert(book: Item) {
        bookEntityQuery.insertOrReplace(
            title = book.volumeInfo.title,
            image = book.volumeInfo.imageLinks?.smallThumbnail ?: "",
            link = book.volumeInfo.previewLink
        )
    }

    suspend fun deleteAll() {
        bookEntityQuery.empty()
    }

    suspend fun deleteBook(book: Book) {
        bookEntityQuery.deleteByLabel(book.title)
    }

}
