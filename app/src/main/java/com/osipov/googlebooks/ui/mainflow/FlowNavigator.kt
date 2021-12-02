package com.osipov.googlebooks.ui.mainflow

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen

open class FlowNavigator(
    activity: FragmentActivity,
    containerId: Int,
    fragmentManager: FragmentManager = activity.supportFragmentManager,
    fragmentFactory: FragmentFactory = fragmentManager.fragmentFactory
) : AppNavigator(activity, containerId, fragmentManager, fragmentFactory) {

    override fun applyCommand(command: Command) {
        super.applyCommand(command)
        if (command is FlowCommand) {
            val screen = command.screen as FragmentScreen
            val currentFragment =
                fragmentManager.fragments.firstOrNull { it.tag == screen.screenKey }
            if (currentFragment != null) {
                showFlowFragment(currentFragment)
            } else {
                createNewFragment(screen)
            }
        }
    }

    private fun showFlowFragment(currentFragment: Fragment) {
        fragmentManager.commit {
            setReorderingAllowed(true)
            fragmentManager.fragments.filter { it !is MainFlowFragment }.forEach {
                this.hide(it)
            }
            show(currentFragment)
        }
    }

    private fun createNewFragment(fragmentScreen: FragmentScreen) {
        val fragment = fragmentScreen.createFragment(fragmentFactory)
        fragmentManager.commit {
            setReorderingAllowed(true)
            fragmentManager.fragments.filter { it !is MainFlowFragment }.forEach {
                this.hide(it)
            }
            add(containerId, fragment, fragmentScreen.screenKey)
            addToBackStack(fragmentScreen.screenKey)
            localStackCopy.add(fragmentScreen.screenKey)
        }
    }

}
