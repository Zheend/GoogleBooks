package com.osipov.googlebooks.presentation.view

import android.os.Bundle
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.osipov.googlebooks.R
import com.osipov.googlebooks.di.DI
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val presenter: MainPresenter by moxyPresenter {
        Toothpick.openScope(DI.APP_SCOPE).getInstance(MainPresenter::class.java)
    }

    private val navigator: Navigator by lazy {
        object : AppNavigator(this, R.id.mainContainer, supportFragmentManager) {
            override fun activityBack() {
                router.exit()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.inject(this, Toothpick.openScope(DI.APP_SCOPE))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}