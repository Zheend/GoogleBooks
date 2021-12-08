package com.osipov.googlebooks.utils

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.osipov.googlebooks.domain.model.BookEntity
import com.osipov.googlebooks.presentation.adapter.booksAdapterDelegate

class MainBooksAdapter(
    itemClick: ((String) -> Unit),
    favoriteClick: ((BookEntity) -> Unit),
    showDetails: ((BookEntity) -> Unit)
) : ListDelegationAdapter<List<Any>>(booksAdapterDelegate(itemClick, favoriteClick, showDetails))