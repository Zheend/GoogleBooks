package com.osipov.googlebooks.domain

import android.util.Log
import com.osipov.googlebooks.data.local.BooksDao
import com.osipov.googlebooks.data.remote.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(
    private val booksDao: BooksDao
) {

    suspend fun addToFavorite(book: Item) = withContext(Dispatchers.IO) {
        booksDao.upsert(book)
    }

}