package com.osipov.googlebooks.utils

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
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