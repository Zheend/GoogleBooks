package com.osipov.googlebooks.ui.mainflow

import com.osipov.googlebooks.ui.base.BasePresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class MainFlowPresenter @Inject constructor(): BasePresenter<MainFlowView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.setupBottomNavigation()
        viewState.setupViewPager()
    }

    fun onMenuAllClicked() {
        viewState.onMenuAllClicked()
    }

    fun onMenuFavoriteClicked() {
        viewState.onMenuFavoriteClicked()
    }

    fun onMenuSettingsClicked() {
        viewState.onMenuSettingsClicked()
    }

}
