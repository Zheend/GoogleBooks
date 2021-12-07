package com.osipov.googlebooks.presentation.view

import com.osipov.googlebooks.presentation.base.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MainFlowView : BaseView {
    fun setupBottomNavigation()
}