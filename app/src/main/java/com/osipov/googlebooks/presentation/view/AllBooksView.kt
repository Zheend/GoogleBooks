package com.osipov.googlebooks.presentation.view

import com.osipov.googlebooks.domain.model.BookEntity
import com.osipov.googlebooks.presentation.base.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface AllBooksView : BaseView {

    @AddToEndSingle
    fun setBooks(books: List<BookEntity>)

    @AddToEndSingle
    fun showState(isEmptyQuery: Boolean)
}