package com.osipov.googlebooks.presentation.adapter

import coil.load
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.osipov.googlebooks.R
import com.osipov.googlebooks.databinding.BookItemBinding
import com.osipov.googlebooks.domain.model.BookEntity
import com.osipov.googlebooks.utils.makeClickable

fun booksAdapterDelegate(
    itemClick: ((String) -> Unit),
    favoriteClick: ((BookEntity) -> Unit),
    showDetails: ((BookEntity) -> Unit)
) = adapterDelegateViewBinding<BookEntity, Any, BookItemBinding>(
    { layoutInflater, parent ->
        BookItemBinding.inflate(layoutInflater, parent, false)
    }
) {
    bind {
        with(binding) {
            smallThumbnail.load(item.image) {
                fallback(R.drawable.ic_error)
            }
            bookTitle.text = item.title
            bookAuthor.text = item.author
            bookLink.apply {
                text = item.link
                makeClickable {
                    itemClick.invoke(text.toString())
                }
            }
            ivFavoriteBook.apply {
                getDrawable(R.drawable.ic_favorite_disabled)
                setOnClickListener {
                    favoriteClick(item)
                }
            }
            root.setOnClickListener {
                showDetails(item)
            }
        }
    }
}
