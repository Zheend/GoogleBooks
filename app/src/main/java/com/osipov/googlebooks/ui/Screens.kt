package com.osipov.googlebooks.ui

import android.content.Intent
import android.net.Uri
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.osipov.googlebooks.ui.allbooks.AllBooksFragment
import com.osipov.googlebooks.ui.favorite.FavoriteFragment
import com.osipov.googlebooks.ui.mainflow.MainFlowFragment

object Screens {

    fun MainFlow() = FragmentScreen { MainFlowFragment() }
    fun AllBooksTab() = FragmentScreen { AllBooksFragment() }
    fun FavoriteTab() = FragmentScreen { FavoriteFragment() }
    fun SettingsTab() = FragmentScreen { FavoriteFragment() }
    fun BrowserScreen(link: String) = ActivityScreen {
        Intent(Intent.ACTION_VIEW, Uri.parse(link))
    }

}