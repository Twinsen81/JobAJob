package jobajob.feature.dashboard.di

import dagger.Component
import jobajob.feature.dashboard.DashboardFragment
import jobajob.library.utils.di.PerFeature
import jobajob.library.utils.di.UtilsApi

@Component(
    modules = [DashboardFeatureModule::class],
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

    internal abstract fun inject(fragment: DashboardFragment)
}