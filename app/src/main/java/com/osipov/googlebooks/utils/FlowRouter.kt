package com.osipov.googlebooks.utils

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.osipov.googlebooks.utils.FlowCommand
import javax.inject.Inject

class FlowRouter @Inject constructor(
    private val appRouter: Router
): Router() {

    fun navigateFlow(screen: FragmentScreen) {
        executeCommands(FlowCommand(screen))
    }

    fun navigate(screen: Screen) {
        appRouter.navigateTo(screen)
    }

    fun back() {
        appRouter.finishChain()
    }
}