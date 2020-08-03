package jobajob.feature.dashboard.devapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import jobajob.feature.dashboard.api.DashboardFeatureApi
import jobajob.feature.dashboard.devapp.R
import jobajob.library.uicomponents.navigation.RootNavigator
import javax.inject.Inject

@AndroidEntryPoint
class DevActivity : AppCompatActivity(), RootNavigator {

    @Inject
    lateinit var dashboardFeatureApi: DashboardFeatureApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dev)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, dashboardFeatureApi.getDashboardFragment())
                .commit()
        }
    }

    override var hideNavigationView: Boolean
        get() = false
        set(value) {}

    override fun onSoftBackButtonPressed() = Unit

    override fun onNeedUserAuthorization() = Unit
}