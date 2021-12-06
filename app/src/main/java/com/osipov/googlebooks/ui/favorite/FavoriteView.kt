package com.osipov.googlebooks.ui.favorite

import com.osipov.googlebooks.data.local.Book
import com.osipov.googlebooks.ui.base.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface FavoriteView : BaseView {

    @AddToEndSingle
    fun setBooksIntoList(books: List<Book>)
}
