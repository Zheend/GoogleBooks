package com.osipov.googlebooks.ui.main

import com.github.terrakok.cicerone.Router
import com.osipov.googlebooks.ui.Screens
import com.osipov.googlebooks.ui.base.BasePresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val router: Router
) : BasePresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(Screens.MainFlow())
    }

}
