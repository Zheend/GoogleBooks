package com.osipov.googlebooks.presentation.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.osipov.googlebooks.domain.model.BookEntity
import com.osipov.googlebooks.presentation.allbooks.allBooksAdapterDelegate

class MainBooksAdapter(
    itemClick: ((String) -> Unit),
    favoriteClick: ((BookEntity) -> Unit),
    showDetails: ((BookEntity) -> Unit)
) : ListDelegationAdapter<List<Any>>(allBooksAdapterDelegate(itemClick, favoriteClick, showDetails))