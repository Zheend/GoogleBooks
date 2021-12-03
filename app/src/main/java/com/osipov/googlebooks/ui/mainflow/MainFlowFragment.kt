package com.osipov.googlebooks.ui.mainflow

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.osipov.googlebooks.R
import com.osipov.googlebooks.databinding.MainFlowFragmentBinding
import com.osipov.googlebooks.di.mainFlowModule
import com.osipov.googlebooks.ui.base.BaseFragment
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
        object : FlowNavigator(requireActivity(), R.id.mainFlowContainer) {
            override fun activityBack() {
                flowRouter.exit()
            }
        }
    }

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

}