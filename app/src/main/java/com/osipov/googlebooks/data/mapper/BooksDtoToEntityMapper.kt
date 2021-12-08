package com.osipov.googlebooks.data.mapper

import com.osipov.googlebooks.data.model.BookDto
import com.osipov.googlebooks.domain.model.BookEntity
import comosipovgooglebooks.entity.BookTable
import javax.inject.Inject

class BooksDtoToEntityMapper @Inject constructor() {

    fun mapBookEntityList(bookDto: BookDto): List<BookEntity> {
        val bookEntities = mutableListOf<BookEntity>()
        bookDto.items?.forEach {
            bookEntities.add(
                BookEntity(
                    title = it.volumeInfo.title,
                    image = it.volumeInfo.imageLinks?.smallThumbnail ?: "",
                    author = it.volumeInfo.authors?.getOrNull(0) ?: "",
                    link = it.volumeInfo.previewLink,
                    isFavorite = false
                )
            )
        }
        return bookEntities
    }

    fun mapBookEntityListFromBookTableList(bookTableList: List<BookTable>): List<BookEntity> = bookTableList.map {
        mapBookEntityFromBookTable(it)
    }

    private fun mapBookEntityFromBookTable(bookTable: BookTable): BookEntity = BookEntity(
        title = bookTable.title,
        image = bookTable.image,
        author = bookTable.author,
        link = bookTable.link.orEmpty(),
        isFavorite = true
    )

}
