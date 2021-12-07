package com.osipov.googlebooks.screens

import android.content.Intent
import android.net.Uri
import androidx.core.os.bundleOf
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.osipov.googlebooks.data.model.Item
import com.osipov.googlebooks.domain.model.BookEntity
import com.osipov.googlebooks.presentation.view.DetailsBookFragment
import com.osipov.googlebooks.presentation.view.AllBooksFragment
import com.osipov.googlebooks.presentation.view.FavoriteFragment
import com.osipov.googlebooks.presentation.view.MainFlowFragment

object Screens {

    fun MainFlow() = FragmentScreen { MainFlowFragment() }
    fun AllBooksTab() = FragmentScreen { AllBooksFragment() }
    fun FavoriteTab() = FragmentScreen { FavoriteFragment() }
    fun SettingsTab() = FragmentScreen { FavoriteFragment() }
    fun BrowserScreen(link: String) = ActivityScreen {
        Intent(Intent.ACTION_VIEW, Uri.parse(link))
    }
    fun DetailsBook(book: BookEntity) = FragmentScreen {
        DetailsBookFragment().apply {
            arguments = bundleOf(DETAILS_BOOK to book)
        }
    }

}

private const val DETAILS_BOOK = "details_book"