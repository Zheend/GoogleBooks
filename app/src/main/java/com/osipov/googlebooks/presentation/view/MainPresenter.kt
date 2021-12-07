package com.osipov.googlebooks.presentation.view

import com.github.terrakok.cicerone.Router
import com.osipov.googlebooks.presentation.base.BasePresenter
import com.osipov.googlebooks.screens.Screens
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
