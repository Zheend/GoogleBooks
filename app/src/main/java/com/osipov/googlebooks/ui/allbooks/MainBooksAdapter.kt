package com.osipov.googlebooks.ui.allbooks

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class MainBooksAdapter(itemClick: ((String) -> Unit)) : ListDelegationAdapter<List<Any>>(
    allBooksAdapterDelegate(itemClick)
)