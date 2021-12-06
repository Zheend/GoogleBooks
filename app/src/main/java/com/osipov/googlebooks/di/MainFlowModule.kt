package com.osipov.googlebooks.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.osipov.googlebooks.data.local.BooksDao
import com.osipov.googlebooks.ui.mainflow.FlowRouter
import com.squareup.sqldelight.db.SqlDriver
import toothpick.ktp.binding.module

fun mainFlowModule(appRouter: Router) = module {

    val cicerone = Cicerone.create(FlowRouter(appRouter))
    bind(FlowRouter::class.java).toInstance(cicerone.router)
    bind(NavigatorHolder::class.java).toInstance(cicerone.getNavigatorHolder())

}
