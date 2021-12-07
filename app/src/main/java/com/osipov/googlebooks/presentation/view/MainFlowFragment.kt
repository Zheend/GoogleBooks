package com.osipov.googlebooks.presentation.view

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.osipov.googlebooks.R
import com.osipov.googlebooks.databinding.MainFlowFragmentBinding
import com.osipov.googlebooks.di.mainFlowModule
import com.osipov.googlebooks.presentation.base.BaseFragment
import com.osipov.googlebooks.presentation.presenter.MainFlowPresenter
import com.osipov.googlebooks.utils.FlowNavigator
import com.osipov.googlebooks.utils.FlowRouter
import moxy.ktx.moxyPresenter
import toothpick.Scope
import toothpick.Toothpick
import javax.inject.Inject

class MainFlowFragment : BaseFragment(R.layout.main_flow_fragment), MainFlowView {

    private val mainFlowPresenter: MainFlowPresenter by moxyPresenter { scope.getInstance(MainFlowPresenter::class.java) }
    private val binding by viewBinding(MainFlowFragmentBinding::bind)

    @Inject
    lateinit var flowRouter: FlowRouter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator by lazy {
        object : FlowNavigator(requireActivity(), R.id.mainFlowContainer, childFragmentManager) {
            override fun activityBack() {
                flowRouter.exit()
            }
        }
    }

    private val currentFragment
        get() = childFragmentManager.findFragmentById(R.id.mainFlowContainer) as? BaseFragment

    override fun installModules(scope: Scope) {
        super.installModules(scope)
        scope.installModules(mainFlowModule(scope.getInstance(Router::class.java)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, scope)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun setupBottomNavigation() {
        binding.mainBottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuAllBottomNavigation -> mainFlowPresenter.onMenuAllClicked()
                R.id.menuFavoriteBottomNavigation -> mainFlowPresenter.onMenuFavoriteClicked()
                R.id.menuSettingBottomNavigation -> mainFlowPresenter.onMenuSettingsClicked()
            }
            true
        }
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed()
    }

}