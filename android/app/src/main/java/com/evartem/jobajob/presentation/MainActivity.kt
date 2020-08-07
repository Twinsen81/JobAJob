package com.evartem.jobajob.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
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
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var loginFeatureApi: LoginFeatureApi

    @Inject
    lateinit var dashboardFeatureApi: DashboardFeatureApi

    @Inject
    lateinit var favoritesFeatureApi: FavoritesFeatureApi

    @Inject
    lateinit var screenNavigator: ScreenNavigator

    private val tabs = Tabs(
        listOf(
            R.id.navigation_dashboard to Provider { dashboardFeatureApi.getDashboardFragment() },
            R.id.navigation_favorites to Provider { favoritesFeatureApi.getFavoritesFragment() },
            R.id.navigation_resumes to Provider { StubFragment.newInstance("RESUMES") },
            R.id.navigation_more to Provider { StubFragment.newInstance("MORE...") }
        )
    )

    companion object {
        private const val NOTIFICATION_KEY = "NOTIFICATION_KEY"

        fun createNotificationIntent(context: Context, message: RemoteMessage): Intent {
            val notificationIntent = Intent(context, MainActivity::class.java)
            notificationIntent.putExtra(NOTIFICATION_KEY, message)
            return notificationIntent
        }
    }

/*    override var hideNavigationView: Boolean = false
        set(hideMenu) {
            bottom_navigation.visibility = if (hideMenu) View.GONE else View.VISIBLE
            field = hideMenu
        }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(this::onBottomNavigationItemSelected)

        screenNavigator.initialize(
            //TODO: commonBackStack = true,
            fragmentManager = supportFragmentManager,
            containerId = R.id.main_container,
            tabsNumber = 4,
            initialTabIndex = 0,
            savedInstanceState = savedInstanceState,
            rootTabFragmentCreator = this::createRootFragment,
            //TODO: onSwitchToFullScreen - make one StateChanged method instead of these three !
            onTabChanged = this::onTabChanged,
            onFragmentChanged = this::onFragmentChanged
        )
    }

    private fun createRootFragment(tabIndex: Int): Fragment = tabs.getFragmentByIndex(tabIndex)

    @Suppress("UNUSED_PARAMETER")
    private fun onTabChanged(fragment: Fragment?, tabIndex: Int) {
        val newSelectedItemId = tabs.getNavMenuIdByIndex(tabIndex)
        if (bottom_navigation.selectedItemId != newSelectedItemId)
            bottom_navigation.selectedItemId = newSelectedItemId
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onFragmentChanged(fragment: Fragment?, transactionType: FragmentTransactionType) = Unit

    private fun onBottomNavigationItemSelected(item: MenuItem): Boolean {
        screenNavigator.switchTab(tabs.getTabIndexByNavMenuId(item.itemId))
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        screenNavigator.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        if (!screenNavigator.goBack()) super.onBackPressed()
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
