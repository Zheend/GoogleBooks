package com.osipov.googlebooks.utils

import com.osipov.googlebooks.domain.model.BookEntity
import comosipovgooglebooks.entity.BookTable

fun Any.objectScopeName() = "${javaClass.simpleName}_${hashCode()}"

fun BookTable.toBookItem() = BookEntity(
    title = title,
    image = image,
    author = author,
    link = link.orEmpty()
)

fun List<BookTable>.toBookList(): List<BookEntity> = map {
    it.toBookItem()
}