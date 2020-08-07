package com.evartem.jobajob.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.evartem.jobajob.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import jobajob.feature.dashboard.api.DashboardFeatureApi
import jobajob.feature.favorites.api.FavoritesFeatureApi
import jobajob.feature.login.api.LoginFeatureApi
import jobajob.library.navigation.api.FragmentTransactionType
import jobajob.library.navigation.api.ScreenNavigator
import jobajob.library.uicomponents.navigation.RootNavigator
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RootNavigator {

    @Inject
    lateinit var loginFeatureApi: LoginFeatureApi

    @Inject
    lateinit var dashboardFeatureApi: DashboardFeatureApi

    @Inject
    lateinit var favoritesFeatureApi: FavoritesFeatureApi

    @Inject
    lateinit var screenNavigator: ScreenNavigator


    companion object {
        private const val NOTIFICATION_KEY = "NOTIFICATION_KEY"

        fun createNotificationIntent(context: Context, message: RemoteMessage): Intent {
            val notificationIntent = Intent(context, MainActivity::class.java)
            notificationIntent.putExtra(NOTIFICATION_KEY, message)
            return notificationIntent
        }
    }

    override var hideNavigationView: Boolean = false
        set(hideMenu) {
            bottom_navigation.visibility = if (hideMenu) View.GONE else View.VISIBLE
            field = hideMenu
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(this::onBottomNavigationItemSelected)

        screenNavigator.initialize(
            fragmentManager = supportFragmentManager,
            containerId = R.id.main_container,
            tabsNumber = 4,
            initialTabIndex = 0,
            savedInstanceState = savedInstanceState,
            rootTabFragmentCreator = this::createRootFragment,
            onTabChanged = this::onTabChanged,
            onFragmentChanged = this::onFragmentChanged
        )
    }

    private fun createRootFragment(tabIndex: Int): Fragment {
        return when (tabIndex) {
            0 -> dashboardFeatureApi.getDashboardFragment()
            1 -> favoritesFeatureApi.getFavoritesFragment()
            2 -> StubFragment.newInstance("RESUMES")
            3 -> StubFragment.newInstance("MORE...")
            else -> throw IllegalArgumentException("Unknown root fragment with index $tabIndex")
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onTabChanged(fragment: Fragment?, tabIndex: Int) = Unit

    @Suppress("UNUSED_PARAMETER")
    private fun onFragmentChanged(fragment: Fragment?, transactionType: FragmentTransactionType) = Unit

/*
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
    }*/

    private fun onBottomNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_dashboard -> screenNavigator.switchTab(0)
            R.id.navigation_favorites -> screenNavigator.switchTab(1)
            R.id.navigation_resumes -> screenNavigator.switchTab(2)
            R.id.navigation_more -> screenNavigator.switchTab(3)
            else -> throw IllegalArgumentException("Unknown bottom navigation item with id: ${item.itemId}")
        }
        return true
    }
/*
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
    }*/

    override fun onSaveInstanceState(outState: Bundle) {
        screenNavigator.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onSoftBackButtonPressed() = onBackPressed()

    override fun onBackPressed() {
        if (!screenNavigator.goBack()) super.onBackPressed()
    }

    override fun onNeedUserAuthorization() {
        startActivity(loginFeatureApi.getLoginScreenIntent(this))
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent?.also {

            // TODO: Refactor the intent's payload type and handle data from both - system tray
            //  and the FCMService.onMessageReceived
            if (it.hasExtra(NOTIFICATION_KEY)) {
                val message: RemoteMessage = it.getParcelableExtra(NOTIFICATION_KEY)!!
                Snackbar.make(
                    mainLayout,
                    message.notification?.body ?: "FCM message",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}
