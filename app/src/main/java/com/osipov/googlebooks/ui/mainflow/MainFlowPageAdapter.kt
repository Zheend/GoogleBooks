package com.osipov.googlebooks.ui.mainflow

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.osipov.googlebooks.ui.allbooks.AllBooksFragment
import com.osipov.googlebooks.ui.favorite.FavoriteFragment
import javax.inject.Inject

class MainFlowPageAdapter @Inject constructor(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment = when (position) {
        MainFlowScreens.ALL_BOOKS.value -> AllBooksFragment()
        MainFlowScreens.FAVORITE.value -> FavoriteFragment()
        MainFlowScreens.SETTINGS.value -> AllBooksFragment()
        else -> AllBooksFragment()
    }

    override fun getItemCount(): Int = MainFlowScreens.values().size
}

enum class MainFlowScreens(val value: Int) {
    ALL_BOOKS(0), FAVORITE(1), SETTINGS(2)
}