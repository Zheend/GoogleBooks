package com.osipov.googlebooks.ui.mainflow

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class FlowRouter @Inject constructor(

): Router() {

    fun navigateFlow(screen: FragmentScreen) {
        executeCommands(FlowCommand(screen))
    }
}