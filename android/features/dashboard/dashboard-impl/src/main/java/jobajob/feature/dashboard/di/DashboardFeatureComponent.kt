package jobajob.feature.dashboard.di

import androidx.fragment.app.Fragment
import dagger.Component
import jobajob.feature.dashboard.presentation.main.DashboardFragment
import jobajob.feature.dashboard.presentation.vacancies.VacanciesFragment
import jobajob.feature.dashboard.presentation.vacancydetail.VacancyDetailFragment
import jobajob.library.uicomponents.navigation.FeatureNavigationModule
import jobajob.library.utils.di.PerFeature
import jobajob.library.utils.di.UtilsApi

@Component(
    modules = [DashboardFeatureModule::class, FeatureNavigationModule::class],
    dependencies = [DashboardFeatureDependencies::class]
)
@PerFeature
abstract class DashboardFeatureComponent : DashboardFeatureApi {

    companion object {
        private var featureComponent: DashboardFeatureComponent? = null

        fun initAndGet(dependencies: DashboardFeatureDependencies): DashboardFeatureApi {
            if (featureComponent == null) {
                synchronized(DashboardFeatureComponent::class) {
                    if (featureComponent == null) {
                        featureComponent = DaggerDashboardFeatureComponent.builder()
                            .dashboardFeatureDependencies(dependencies)
                            .build()
                    }
                }
            }
            return featureComponent!!
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
    interface FeatureDependenciesComponent : DashboardFeatureDependencies

    override fun getDashboardFragment(): Fragment = DashboardFragment()

    internal abstract fun inject(fragment: DashboardFragment)
    internal abstract fun inject(fragment: VacanciesFragment)
    internal abstract fun inject(fragment: VacancyDetailFragment)
}