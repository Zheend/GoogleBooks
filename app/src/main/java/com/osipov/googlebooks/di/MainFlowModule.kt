package com.osipov.googlebooks.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.osipov.googlebooks.ui.mainflow.FlowRouter
import toothpick.ktp.binding.module

fun mainFlowModule() = module {

    val cicerone = Cicerone.create(FlowRouter())
    bind(FlowRouter::class.java).toInstance(cicerone.router)
    bind(NavigatorHolder::class.java).toInstance(cicerone.getNavigatorHolder())

}
