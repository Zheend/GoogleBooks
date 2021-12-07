package com.osipov.googlebooks.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class BookEntity(
    val title: String,
    val image: String,
    val author: String,
    val link: String
) : Parcelable