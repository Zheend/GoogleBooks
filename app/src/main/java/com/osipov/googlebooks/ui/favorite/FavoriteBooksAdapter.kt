package com.osipov.googlebooks.ui.favorite

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.osipov.googlebooks.data.local.Book
import com.osipov.googlebooks.ui.allbooks.favoriteBooksAdapterDelegate

class FavoriteBooksAdapter(itemClick: ((String) -> Unit), favoriteClick: ((Book) -> Unit)) :
    ListDelegationAdapter<List<Any>>(favoriteBooksAdapterDelegate(itemClick, favoriteClick))