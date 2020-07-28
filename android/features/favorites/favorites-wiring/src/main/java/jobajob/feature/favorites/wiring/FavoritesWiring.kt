package jobajob.feature.favorites.wiring

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import jobajob.feature.favorites.api.FavoritesFeatureApi
import jobajob.feature.favorites.api.FavoritesFeatureApiImpl

@Module
@InstallIn(ApplicationComponent::class)
internal object FavoritesWiring {

    @Provides
    fun provideFavoritesFeature(apiImpl: FavoritesFeatureApiImpl): FavoritesFeatureApi = apiImpl
}