package com.osipov.googlebooks.di

import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.osipov.googlebooks.Database
import com.osipov.googlebooks.data.remote.BooksService
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import toothpick.ktp.binding.module

fun appModule(context: Context) = module {
    //Global
    bind(Context::class.java).toInstance(context)

    // Navigation
    val cicerone = Cicerone.create()
    bind(Router::class.java).toInstance(cicerone.router)
    bind(NavigatorHolder::class.java).toInstance(cicerone.getNavigatorHolder())

    //Api
    bind(BooksService::class.java).toInstance(BooksService.create())

    //Database
    bind(SqlDriver::class.java).toInstance(
        AndroidSqliteDriver(
            schema = Database.Schema,
            context = context,
            name = "books_sqldelight.db"
        )
    )

}
