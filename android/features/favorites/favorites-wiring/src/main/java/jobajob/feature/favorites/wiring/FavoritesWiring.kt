package jobajob.feature.favorites.wiring

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import jobajob.feature.favorites.api.FavoritesFeatureApi
import jobajob.feature.favorites.api.FavoritesFeatureApiImpl

@Module
@InstallIn(ActivityComponent::class)
object FavoritesWiring {

    @Provides
    fun provideFavoritesFeature(): FavoritesFeatureApi = FavoritesFeatureApiImpl
}