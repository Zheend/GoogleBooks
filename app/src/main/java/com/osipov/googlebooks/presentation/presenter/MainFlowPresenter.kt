package com.osipov.googlebooks.presentation.presenter

import com.osipov.googlebooks.screens.Screens
import com.osipov.googlebooks.presentation.base.BasePresenter
import com.osipov.googlebooks.utils.FlowRouter
import com.osipov.googlebooks.presentation.view.MainFlowView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class MainFlowPresenter @Inject constructor(
    private val router: FlowRouter
) : BasePresenter<MainFlowView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.setupBottomNavigation()
        onMenuAllClicked()
    }

    fun onMenuAllClicked() {
        router.navigateFlow(Screens.AllBooksTab())
    }

    fun onMenuFavoriteClicked() {
        router.navigateFlow(Screens.FavoriteTab())
    }

    fun onMenuSettingsClicked() {
        router.navigateFlow(Screens.FavoriteTab())
    }

}
