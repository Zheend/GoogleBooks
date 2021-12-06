package com.osipov.googlebooks.ui.allbooks

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import coil.load
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.osipov.googlebooks.R
import com.osipov.googlebooks.data.local.Book
import com.osipov.googlebooks.data.remote.Item
import com.osipov.googlebooks.databinding.BookItemBinding

fun allBooksAdapterDelegate(itemClick: ((String) -> Unit), favoriteClick: ((Item) -> Unit)) =
    adapterDelegateViewBinding<Item, Any, BookItemBinding>(
        { layoutInflater, parent ->
            BookItemBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            with(binding) {
                smallThumbnail.load(item.volumeInfo.imageLinks?.smallThumbnail) {
                    fallback(R.drawable.ic_error)
                }
                bookTitle.text = item.volumeInfo.title
                bookAuthor.text = item.volumeInfo.authors?.firstOrNull() ?: "No author"
                bookLink.apply {
                    text = item.volumeInfo.previewLink
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
            }
        }
    }

fun favoriteBooksAdapterDelegate(itemClick: ((String) -> Unit), favoriteClick: (Book) -> Unit) =
    adapterDelegateViewBinding<Book, Any, BookItemBinding>(
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
                bookAuthor.text = item.title
                bookLink.apply {
                    text = item.link
                    makeClickable {
                        itemClick.invoke(text.toString())
                    }
                }
                ivFavoriteBook.apply {
                    setImageDrawable(getDrawable(R.drawable.ic_favorite_enabled))
                    setOnClickListener {
                        favoriteClick(item)
                    }
                }
            }
        }
    }

fun TextView.makeClickable(action: () -> Unit) {
    val text = this.text.toString()
    val spannableString = SpannableString(text)

    val clickableSpan: ClickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View) {
            action()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = Color.parseColor("#6688C8")
        }
    }
    spannableString.setSpan(
        clickableSpan,
        0,
        text.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    this.text = spannableString
    this.movementMethod = LinkMovementMethod.getInstance()
}