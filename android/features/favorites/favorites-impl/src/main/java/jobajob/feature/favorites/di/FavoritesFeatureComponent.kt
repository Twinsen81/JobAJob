package jobajob.feature.favorites.di

import androidx.fragment.app.Fragment
import dagger.Component
import jobajob.feature.favorites.navigation.FavoritesNavigationFragment
import jobajob.feature.favorites.presentation.favorites.FavoritesFragment
import jobajob.library.uicomponents.navigation.FeatureNavigationModule
import jobajob.library.utils.di.PerFeature
import jobajob.library.utils.di.UtilsApi
import jobajon.feature.favorites.di.FavoritesFeatureApi

@Component(
    modules = [ViewModelModule::class, FeatureNavigationModule::class],
    dependencies = [FeatureDependencies::class]
)
@PerFeature
abstract class FavoritesFeatureComponent : FavoritesFeatureApi {

    companion object {
        private var featureComponent: FavoritesFeatureComponent? = null

        fun initAndGet(dependencies: FeatureDependencies): FavoritesFeatureApi {
            if (featureComponent == null) {
                synchronized(FavoritesFeatureComponent::class) {
                    if (featureComponent == null) {
                        featureComponent = DaggerFavoritesFeatureComponent.builder()
                            .featureDependencies(dependencies)
                            .build()
                    }
                }
            }
            return featureComponent!!
        }

        fun get(): FavoritesFeatureComponent {
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

    override fun getFavoritesFragment(): Fragment = FavoritesFragment()

    internal abstract fun inject(fragment: FavoritesNavigationFragment)
    internal abstract fun inject(fragment: FavoritesFragment)
}