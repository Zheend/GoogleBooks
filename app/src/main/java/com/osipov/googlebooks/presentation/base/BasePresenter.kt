package com.osipov.googlebooks.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import moxy.MvpPresenter
import moxy.MvpView

open class BasePresenter<V : MvpView> :
    MvpPresenter<V>(),
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    override fun onDestroy() {
        cancel()
    }
}