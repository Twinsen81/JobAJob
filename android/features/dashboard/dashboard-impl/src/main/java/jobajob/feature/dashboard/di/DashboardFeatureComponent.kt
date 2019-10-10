package jobajob.feature.dashboard.di

import androidx.fragment.app.Fragment
import dagger.Component
import jobajob.feature.dashboard.presentation.navigation.DashboardNavigationFragment
import jobajob.feature.dashboard.presentation.vacancies.VacanciesFragment
import jobajob.feature.dashboard.presentation.vacancydetail.VacancyDetailFragment
import jobajob.library.uicomponents.navigation.FeatureNavigationModule
import jobajob.library.utils.di.PerFeature
import jobajob.library.utils.di.UtilsApi

@Component(
    modules = [ViewModelModule::class, FeatureNavigationModule::class, NetworkModule::class, DataModule::class],
    dependencies = [FeatureDependencies::class]
)
@PerFeature
abstract class DashboardFeatureComponent : DashboardFeatureApi {

    companion object {
        private var featureComponent: DashboardFeatureComponent? = null

        fun initAndGet(dependencies: FeatureDependencies): DashboardFeatureApi =
            featureComponent ?: synchronized(this) {
                featureComponent ?: DaggerDashboardFeatureComponent.builder()
                    .featureDependencies(dependencies)
                    .build().also { featureComponent = it }
            }

        fun get(): DashboardFeatureComponent {
            require(featureComponent != null) { "You must call initAndGet prior this call!" }
            return featureComponent!!
        }

        fun resetComponent() {
            featureComponent = null
        }
    }

    @Component(dependencies = [UtilsApi::class])
    @PerFeature
    interface FeatureDependenciesComponent : FeatureDependencies

    override fun getDashboardFragment(): Fragment = DashboardNavigationFragment()

    internal abstract fun inject(fragment: DashboardNavigationFragment)
    internal abstract fun inject(fragment: VacanciesFragment)
    internal abstract fun inject(fragment: VacancyDetailFragment)
}