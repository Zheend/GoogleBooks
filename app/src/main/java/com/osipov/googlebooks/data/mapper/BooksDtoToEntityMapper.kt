package com.osipov.googlebooks.data.mapper

import com.osipov.googlebooks.data.model.BookDto
import com.osipov.googlebooks.domain.model.BookEntity
import javax.inject.Inject

class BooksDtoToEntityMapper @Inject constructor() {

    fun mapBookEntityList(bookDto: BookDto): List<BookEntity> {
        val bookEntities = mutableListOf<BookEntity>()
        bookDto.items.forEach {
            bookEntities.add(
                BookEntity(
                    title = it.volumeInfo.title,
                    image = it.volumeInfo.imageLinks?.smallThumbnail ?: "",
                    author = it.volumeInfo.authors?.getOrNull(0) ?: "",
                    link = it.volumeInfo.previewLink
                )
            )
        }
        return bookEntities
    }

}
