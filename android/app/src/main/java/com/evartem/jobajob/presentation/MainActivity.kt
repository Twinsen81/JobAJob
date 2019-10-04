package com.evartem.jobajob.presentation

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.evartem.jobajob.R
import com.evartem.jobajob.di.AppComponent
import com.evartem.jobajob.di.FeatureInjector
import jobajob.feature.dashboard.di.DashboardFeatureComponent
import jobajob.feature.favorites.di.FavoritesFeatureComponent
import jobajob.library.uicomponents.navigation.BackButtonHandler
import jobajob.library.uicomponents.navigation.RootNavigator
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), RootNavigator {

    @Inject
    lateinit var features: FeatureInjector

    @Inject
    lateinit var navigationTabs: NavigationTabs

    private lateinit var fragManager: FragmentManager

    private var tabsBackStack = mutableListOf<NavigationTab>()

    companion object {
        private const val VISIBLE_TAB_KEY = "VISIBLE_TAB_KEY"
        private const val BACKSTACK_KEY = "BACKSTACK_KEY"
    }

    override var hideNavigationView: Boolean = false
        set(hideMenu) {
            bottom_navigation.visibility = if (hideMenu) View.GONE else View.VISIBLE
            field = hideMenu
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppComponent.get().inject(this)

        fragManager = supportFragmentManager

        bottom_navigation.setOnNavigationItemSelectedListener(this::onBottomNavigationItemSelected)

        if (savedInstanceState != null)
            restoreState(savedInstanceState)
        else
            onBottomNavigationItemSelected(
                bottom_navigation.menu.findItem(
                    navigationTabs.getMenuItemIdForTab(
                        NavigationTab.DASHBOARD
                    )
                )
            )
    }

    private fun restoreState(state: Bundle) {

        if (state.containsKey(VISIBLE_TAB_KEY)) {
            val visibleTab = NavigationTab.valueOf(
                state.getString(
                    VISIBLE_TAB_KEY
                )!!
            )
            hideAllTabsBut(visibleTab)
        }

        if (state.containsKey(BACKSTACK_KEY))
            tabsBackStack = state.getStringArray(BACKSTACK_KEY)!!
                .toList().map { NavigationTab.valueOf(it) }
                .toMutableList()
    }

    private fun onBottomNavigationItemSelected(item: MenuItem): Boolean {
        navigateOrBackTo(navigationTabs.getTabForMenuItemId(item.itemId))
        return true
    }

    private fun navigateOrBackTo(tab: NavigationTab) {
        val currentVisibleFragment: Fragment? = fragManager.fragments.firstOrNull { it.isVisible }
        val existingFragmentToShow = fragManager.findFragmentByTag(tab.name)

        if (currentVisibleFragment != null && existingFragmentToShow != null && currentVisibleFragment === existingFragmentToShow) return

        val transaction = fragManager.beginTransaction()

        if (existingFragmentToShow == null)
            transaction.add(R.id.main_container, navigationTabs.getFragmentForTab(tab), tab.name)

        if (currentVisibleFragment != null) transaction.hide(currentVisibleFragment)

        if (existingFragmentToShow != null) transaction.show(existingFragmentToShow)

        transaction.commitNow()
        setTopOfBackStack(tab)

        bottom_navigation.menu.findItem(navigationTabs.getMenuItemIdForTab(tab)).isChecked = true
    }

    private fun hideAllTabsBut(tab: NavigationTab) {
        val transaction = fragManager.beginTransaction()
        fragManager.fragments.forEach {
            if (it.tag != tab.name)
                transaction.hide(it)
        }
        transaction.commitNow()
    }

    private fun setTopOfBackStack(tab: NavigationTab) {
        val existingIndex = tabsBackStack.indexOf(tab)
        if (existingIndex == -1) {
            tabsBackStack.add(tab)
            return
        }

        tabsBackStack = tabsBackStack.subList(0, existingIndex + 1)
    }

    private fun goBackTabsBackStack(): NavigationTab? {
        if (tabsBackStack.size > 1) {
            tabsBackStack.removeAt(tabsBackStack.size - 1)
            return tabsBackStack.last()
        }
        return null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        tabsBackStack.lastOrNull()?.also { tab ->
            outState.putString(VISIBLE_TAB_KEY, tab.name)
            outState.putStringArray(BACKSTACK_KEY, tabsBackStack.map { it.name }.toTypedArray())
        }

        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()

        if (isFinishing) {
            DashboardFeatureComponent.resetComponent()
            FavoritesFeatureComponent.resetComponent()
        }
    }

    override fun onSoftBackButtonPressed() = onBackPressed()

    override fun onBackPressed() {
        val currentVisibleFragment = fragManager.fragments.firstOrNull { it.isVisible }
        if (currentVisibleFragment is BackButtonHandler && currentVisibleFragment.onBackPressed())
            return

        val previousTabToShow = goBackTabsBackStack()
        if (previousTabToShow != null) {
            navigateOrBackTo(previousTabToShow)
            return
        }

        super.onBackPressed()
    }
}
