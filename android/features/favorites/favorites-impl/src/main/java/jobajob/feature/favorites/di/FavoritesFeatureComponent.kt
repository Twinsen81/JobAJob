package jobajob.feature.favorites.di

import dagger.Component
import jobajob.feature.favorites.FavoritesFragment
import jobajob.library.utils.di.PerFeature
import jobajob.library.utils.di.UtilsApi
import jobajon.feature.favorites.di.FavoritesFeatureApi

@Component(
    modules = [FavoritesFeatureModule::class],
    dependencies = [FavoritesFeatureDependencies::class]
)
@PerFeature
abstract class FavoritesFeatureComponent : FavoritesFeatureApi {

    companion object {
        private var featureComponent: FavoritesFeatureComponent? = null

        fun initAndGet(dependencies: FavoritesFeatureDependencies): FavoritesFeatureApi {
            if (featureComponent == null) {
                synchronized(FavoritesFeatureComponent::class) {
                    if (featureComponent == null) {
                        featureComponent = DaggerFavoritesFeatureComponent.builder()
                            .favoritesFeatureDependencies(dependencies)
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
    interface FeatureDependenciesComponent : FavoritesFeatureDependencies

    internal abstract fun inject(fragment: FavoritesFragment)
}