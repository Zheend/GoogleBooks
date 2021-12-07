package com.osipov.googlebooks.presentation.presenter

import com.osipov.googlebooks.presentation.base.BasePresenter
import com.osipov.googlebooks.presentation.view.DetailsBookView
import com.osipov.googlebooks.screens.Screens
import com.osipov.googlebooks.utils.FlowRouter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class DetailsBookPresenter @Inject constructor(
    private val flowRouter: FlowRouter
) : BasePresenter<DetailsBookView>() {

    fun openBookLinkIntoBrowser(link: String) {
        flowRouter.navigate(Screens.BrowserScreen(link))
    }

    fun onBackPressed() {
        flowRouter.back()
    }

}