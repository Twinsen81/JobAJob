package com.evartem.jobajob.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.evartem.jobajob.R
import com.evartem.jobajob.deeplink.DeepLinkDestination
import com.evartem.jobajob.deeplink.DeeplinkParser
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import jobajob.feature.dashboard.api.DashboardFeatureApi
import jobajob.feature.favorites.api.FavoritesFeatureApi
import jobajob.library.navigation.api.NavigationEvent
import jobajob.library.navigation.api.ScreenNavigator
import jobajob.library.uicomponents.analytics.AnalyticsScreenViewEvent
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dashboardFeatureApi: DashboardFeatureApi

    @Inject
    lateinit var favoritesFeatureApi: FavoritesFeatureApi

    @Inject
    lateinit var screenNavigator: ScreenNavigator

    @Inject
    lateinit var deeplinkParser: DeeplinkParser

    private val tabs =
        listOf(
            Tab(0, "dashboard", R.id.navigation_dashboard) { dashboardFeatureApi.getDashboardFragment() },
            Tab(1, "favorites", R.id.navigation_favorites) { favoritesFeatureApi.getFavoritesFragment() },
            Tab(2, "resumes", R.id.navigation_resumes) { StubFragment.newInstance("RESUMES") },
            Tab(3, "more", R.id.navigation_more) { StubFragment.newInstance("MORE...") }
        )

    companion object {
        private const val NOTIFICATION_KEY = "NOTIFICATION_KEY"

        fun createNotificationIntent(context: Context, message: RemoteMessage): Intent {
            val notificationIntent = Intent(context, MainActivity::class.java)
            notificationIntent.putExtra(NOTIFICATION_KEY, message)
            return notificationIntent
        }
    }

    private var hideNavigationView: Boolean = false
        set(hide) {
            bottom_navigation.visibility = if (hide) View.GONE else View.VISIBLE
            field = hide
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(this::onBottomNavigationItemSelected)

        screenNavigator.initialize(
            fragmentManager = supportFragmentManager,
            containerId = R.id.main_container,
            tabsNumber = 4,
            savedInstanceState = savedInstanceState,
            rootTabFragmentCreator = this::createRootFragment,
            eventListener = this::onNavigationEvent
        )

        handleDeepLink()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        setIntent(intent)
        handleDeepLink()
/*        intent?.also {

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
        }*/
    }

    private fun handleDeepLink() {
        intent?.data?.let { uri ->
            Timber.v("Handling deeplink: $uri")
            val destination = deeplinkParser.parse(uri)
            if (destination != null) {
                goToDeeplinkDestination(destination)
            } else {
                Timber.w("Failed to handle the deeplink: $uri")
            }
        }
    }

    private fun goToDeeplinkDestination(destination: DeepLinkDestination) {
        val destinationTabIndex = tabs.indexByName(destination.tabName)
        if (destinationTabIndex != null) {
            screenNavigator.switchTab(destinationTabIndex)
            destination.fragment?.also { destinationFragment ->
                screenNavigator.navigateTo(destinationFragment)
            }
        } else {
            Timber.e("Unknown tab \"${destination.tabName}\" in the deeplink: ${destination.deeplink}")
        }
    }

    private fun createRootFragment(tabIndex: Int): Fragment = tabs.fragmentByIndex(tabIndex)

    private fun onNavigationEvent(event: NavigationEvent) {
        when (event) {
            is NavigationEvent.TabChanged -> {
                val newSelectedItemId = tabs.menuIdByIndex(event.tabIndex)
                logTabChanged(newSelectedItemId)
                if (bottom_navigation.selectedItemId != newSelectedItemId)
                    bottom_navigation.selectedItemId = newSelectedItemId
            }
            is NavigationEvent.FragmentChanged -> {
                hideNavigationView = false
            }
            is NavigationEvent.HideNavigation -> {
                hideNavigationView = true
            }
        }

    }

    private fun onBottomNavigationItemSelected(item: MenuItem): Boolean {
        screenNavigator.switchTab(tabs.indexByMenuId(item.itemId))
        return true
    }

    private fun logTabChanged(menuId: Int) = AnalyticsScreenViewEvent(tabs.nameByMenuId(menuId))

    override fun onSaveInstanceState(outState: Bundle) {
        screenNavigator.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        if (!screenNavigator.goBack()) super.onBackPressed()
    }
}
