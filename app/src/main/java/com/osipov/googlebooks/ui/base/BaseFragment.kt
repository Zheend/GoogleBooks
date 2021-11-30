package com.osipov.googlebooks.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.osipov.googlebooks.di.DI
import com.osipov.googlebooks.utils.objectScopeName
import moxy.MvpAppCompatFragment
import toothpick.Scope
import toothpick.Toothpick

private const val STATE_SCOPE_NAME = "state_scope_name"

abstract class BaseFragment(@LayoutRes layoutRes: Int) : MvpAppCompatFragment(layoutRes) {

    protected open val parentScopeName: String by lazy {
        (parentFragment as? BaseFragment)?.fragmentScopeName
            ?: DI.APP_SCOPE
    }

    private lateinit var fragmentScopeName: String
    protected lateinit var scope: Scope
        private set

    protected open fun installModules(scope: Scope) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentScopeName = savedInstanceState?.getString(STATE_SCOPE_NAME) ?: objectScopeName()

        if (Toothpick.isScopeOpen(fragmentScopeName)) {
            scope = Toothpick.openScope(fragmentScopeName)
        } else {
            scope = Toothpick.openScopes(parentScopeName, fragmentScopeName)
            installModules(scope)
        }
        super.onCreate(savedInstanceState)
    }

}