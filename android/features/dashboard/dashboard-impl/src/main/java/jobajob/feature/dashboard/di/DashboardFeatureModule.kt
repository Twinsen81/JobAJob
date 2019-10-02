package jobajob.feature.dashboard.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import jobajob.feature.dashboard.DashboardFragment
import jobajob.feature.dashboard.DashboardViewModel
import jobajob.library.uicomponents.di.FeatureViewModelFactoryModule
import jobajob.library.uicomponents.di.ViewModelKey
import jobajob.library.utils.di.PerFeature

@Module(includes = [FeatureViewModelFactoryModule::class])
abstract class DashboardFeatureModule {

    @[Binds IntoMap ViewModelKey(DashboardViewModel::class)]
    abstract fun bindViewModel(viewModel: DashboardViewModel): ViewModel

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun dashboardFragment(): Fragment = DashboardFragment()
    }
}
