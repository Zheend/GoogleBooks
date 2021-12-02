package com.osipov.googlebooks.ui.mainflow

import com.osipov.googlebooks.ui.base.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MainFlowView : BaseView {
    fun setupBottomNavigation()
}