package com.osipov.googlebooks.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.osipov.googlebooks.data.repository.BooksRepository
import com.osipov.googlebooks.domain.BooksProvider
import com.osipov.googlebooks.domain.interactor.BooksInteractor
import com.osipov.googlebooks.utils.FlowRouter
import toothpick.ktp.binding.module

fun mainFlowModule(appRouter: Router) = module {

    val cicerone = Cicerone.create(FlowRouter(appRouter))
    bind(FlowRouter::class.java).toInstance(cicerone.router)
    bind(NavigatorHolder::class.java).toInstance(cicerone.getNavigatorHolder())

    bind(BooksRepository::class.java).singleton()
    bind(BooksInteractor::class.java).toProvider(BooksProvider::class.java)

}
