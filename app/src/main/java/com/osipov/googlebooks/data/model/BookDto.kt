package com.osipov.googlebooks.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDto(
    @SerialName("totalItems") val totalItems: Int,
    @SerialName("items") val items: List<Item>? = null
)

@Serializable
data class Item(
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    val title: String,
    val authors: List<String>? = emptyList(),
    val imageLinks: ImageLinks? = null,
    val previewLink: String
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)