package com.osipov.googlebooks.ui.allbooks

import com.osipov.googlebooks.data.remote.Item
import com.osipov.googlebooks.ui.base.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface AllBooksView : BaseView {

    @AddToEndSingle
    fun setBooks(books: List<Item>)

    @AddToEndSingle
    fun showState(isEmptyQuery: Boolean)
}