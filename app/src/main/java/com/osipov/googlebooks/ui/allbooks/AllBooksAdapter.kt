package com.osipov.googlebooks.ui.allbooks

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.osipov.googlebooks.R
import com.osipov.googlebooks.data.remote.Item
import com.osipov.googlebooks.databinding.BookItemBinding

class AllBooksAdapter : ListAdapter<Item, AllBooksAdapter.AllBooksViewHolder>(diffUtil) {

    var bookLinkClicked: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllBooksViewHolder {
        val binding: BookItemBinding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllBooksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllBooksViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AllBooksViewHolder(private val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Item) {
            with(binding) {
                smallThumbnail.load(book.volumeInfo.imageLinks?.smallThumbnail) {
                    fallback(R.drawable.ic_error)
                }
                bookTitle.text = book.volumeInfo.title
                bookAuthor.text = book.volumeInfo.authors?.firstOrNull() ?: "No author"
                bookLink.apply {
                    text = book.volumeInfo.previewLink
                    makeClickable {
//                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("$text"))
//                    context.startActivity(intent)
                        bookLinkClicked?.invoke(text.toString())
                    }
                }
            }
        }

    }

}

val diffUtil = object : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item) =
        oldItem.volumeInfo.title == newItem.volumeInfo.title

    override fun areContentsTheSame(oldItem: Item, newItem: Item) =
        oldItem == newItem
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