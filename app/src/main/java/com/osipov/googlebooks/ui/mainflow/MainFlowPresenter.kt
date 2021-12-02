package com.osipov.googlebooks.ui.mainflow

import com.osipov.googlebooks.ui.Screens
import com.osipov.googlebooks.ui.base.BasePresenter
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
