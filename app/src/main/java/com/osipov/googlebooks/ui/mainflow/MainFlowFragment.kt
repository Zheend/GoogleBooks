package com.osipov.googlebooks.ui.mainflow

import by.kirich1409.viewbindingdelegate.viewBinding
import com.osipov.googlebooks.R
import com.osipov.googlebooks.databinding.MainFlowFragmentBinding
import com.osipov.googlebooks.ui.base.BaseFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class MainFlowFragment : BaseFragment(R.layout.main_flow_fragment), MainFlowView {

    @InjectPresenter
    lateinit var mainFlowPresenter: MainFlowPresenter

    @ProvidePresenter
    fun provideMainFlowPresenter(): MainFlowPresenter = scope.getInstance(MainFlowPresenter::class.java)

    private val binding by viewBinding(MainFlowFragmentBinding::bind)

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

    override fun setupViewPager() {
        binding.containerMainFlow.adapter = MainFlowPageAdapter(childFragmentManager, lifecycle)
    }

    override fun onMenuAllClicked() {
        binding.containerMainFlow.setCurrentItem(MainFlowScreens.ALL_BOOKS.value, false)
    }

    override fun onMenuFavoriteClicked() {
        binding.containerMainFlow.setCurrentItem(MainFlowScreens.FAVORITE.value, false)
    }

    override fun onMenuSettingsClicked() {
        binding.containerMainFlow.setCurrentItem(MainFlowScreens.SETTINGS.value, false)
    }

}