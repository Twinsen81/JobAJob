package com.evartem.jobajob

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.evartem.jobajob.di.AppComponent
import com.evartem.jobajob.di.FeatureInjector
import com.evartem.jobajob.navigation.AppNavigator
import com.evartem.jobajob.navigation.AppScreen
import jobajob.feature.dashboard.di.DashboardFeatureComponent
import jobajob.feature.favorites.di.FavoritesFeatureComponent
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var features: FeatureInjector
    @Inject
    lateinit var appNavigator: AppNavigator
    @Inject
    lateinit var appRouter: Router

    private val dashboardScreen = AppScreen("DASHBOARD") {
        features.dashboardFeatureApi().getDashboardFragment()
    }

    private val favoritesScreen = AppScreen("FAVORITES") {
        features.favoritesFeatureApi().getFavoritesFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppComponent.get().inject(this)

        appNavigator.setNavigationHost(this, supportFragmentManager, R.id.main_container)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {

        // Highlight the proper menu item when going back the stack
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount > 0)
                when (supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name) {
                    dashboardScreen.name ->
                        bottom_navigation.menu.findItem(R.id.navigation_dashboard).isChecked = true
                    favoritesScreen.name ->
                        bottom_navigation.menu.findItem(R.id.navigation_favorites).isChecked = true
                }
        }
        bottom_navigation.setOnNavigationItemSelectedListener(this::onBottomNavigationItemSelected)
        onBottomNavigationItemSelected(bottom_navigation.menu.findItem(R.id.navigation_dashboard))
    }

    private fun onBottomNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_dashboard -> appRouter.navigateTo(dashboardScreen)
            R.id.navigation_favorites -> appRouter.navigateTo(favoritesScreen)
        }
        return true
    }

    override fun onResume() {
        super.onResume()

        appNavigator.startNavigator()
    }

    override fun onPause() {
        super.onPause()

        if (isFinishing) {
            DashboardFeatureComponent.resetComponent()
            FavoritesFeatureComponent.resetComponent()
        }

        appNavigator.pauseNavigator()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
            return
        }

        appRouter.exit()
    }
}
