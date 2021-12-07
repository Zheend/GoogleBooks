package com.osipov.googlebooks.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class BookDto(
    val totalItems: Int,
    val items: List<Item>
)

@Serializable
@Parcelize
data class Item(
    val volumeInfo: VolumeInfo
): Parcelable

@Serializable
@Parcelize
data class VolumeInfo(
    val title: String,
    val authors: List<String>? = emptyList(),
    val imageLinks: ImageLinks? = null,
    val previewLink: String
): Parcelable

@Serializable
@Parcelize
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
): Parcelable