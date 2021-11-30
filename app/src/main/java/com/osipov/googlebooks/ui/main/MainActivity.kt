package com.osipov.googlebooks.ui.main

import android.os.Bundle
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.osipov.googlebooks.R
import com.osipov.googlebooks.di.DI
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    private val navigator: Navigator by lazy {
        object : AppNavigator(this, R.id.mainContainer) {
            override fun activityBack() {
                router.exit()
            }
        }
    }

    @ProvidePresenter
    fun provideMainPresenter(): MainPresenter = Toothpick.openScope(DI.APP_SCOPE).getInstance(MainPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.inject(this, Toothpick.openScope(DI.APP_SCOPE))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }
}