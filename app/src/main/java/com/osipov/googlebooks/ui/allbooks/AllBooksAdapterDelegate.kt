package com.osipov.googlebooks.ui.allbooks

import coil.load
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.osipov.googlebooks.R
import com.osipov.googlebooks.data.remote.Item
import com.osipov.googlebooks.databinding.BookItemBinding

fun allBooksAdapterDelegate(itemClick: ((String) -> Unit)) = adapterDelegateViewBinding<Item, Any, BookItemBinding>(
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
//                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("$text"))
//                    context.startActivity(intent)
                    itemClick.invoke(text.toString())
                }
            }
        }
    }

}