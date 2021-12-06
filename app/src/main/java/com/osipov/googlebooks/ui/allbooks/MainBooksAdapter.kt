package com.osipov.googlebooks.ui.allbooks

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.osipov.googlebooks.data.remote.Item

class MainBooksAdapter(itemClick: ((String) -> Unit), favoriteClick: ((Item) -> Unit)) : ListDelegationAdapter<List<Any>>(
    allBooksAdapterDelegate(itemClick, favoriteClick)
)