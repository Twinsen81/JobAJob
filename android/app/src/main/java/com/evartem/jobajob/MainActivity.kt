package com.evartem.jobajob

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.evartem.jobajob.di.AppComponent
import com.evartem.jobajob.di.FeatureInjector
import jobajob.feature.dashboard.di.DashboardFeatureApi
import jobajob.feature.dashboard.di.DashboardFeatureComponent
import jobajob.feature.favorites.di.FavoritesFeatureComponent
import jobajon.feature.favorites.di.FavoritesFeatureApi
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var features: FeatureInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppComponent.get().inject(this)

        bottom_navigation.setOnNavigationItemSelectedListener(this::onBottomNavigationItemSelected)
        onBottomNavigationItemSelected(bottom_navigation.menu.findItem(R.id.navigation_dashboard))
    }

    private fun onBottomNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_dashboard ->
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.main_container,
                        (features.dashboardFeatureComponent() as DashboardFeatureApi).getDashboardFragment()
                    )
                    .commit()
            R.id.navigation_favorites ->
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.main_container,
                        (features.favoritesFeatureComponent() as FavoritesFeatureApi).getFavoritesFragment()
                    )
                    .commit()
        }
        return true
    }

    override fun onPause() {
        super.onPause()

        if (isFinishing) {
            DashboardFeatureComponent.resetComponent()
            FavoritesFeatureComponent.resetComponent()
        }
    }
}
