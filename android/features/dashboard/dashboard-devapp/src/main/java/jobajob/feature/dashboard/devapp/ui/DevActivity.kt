package jobajob.feature.dashboard.devapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import jobajob.feature.dashboard.api.DashboardFeatureApi
import jobajob.feature.dashboard.devapp.R
import jobajob.library.navigation.api.ScreenNavigator
import javax.inject.Inject

@AndroidEntryPoint
class DevActivity : AppCompatActivity() {

    @Inject
    lateinit var dashboardFeatureApi: DashboardFeatureApi

    @Inject
    lateinit var screenNavigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dev)

        screenNavigator.initialize(
            fragmentManager = supportFragmentManager,
            containerId = R.id.main_container,
            rootTabFragmentCreator = { dashboardFeatureApi.getDashboardFragment() }
        )
    }
}