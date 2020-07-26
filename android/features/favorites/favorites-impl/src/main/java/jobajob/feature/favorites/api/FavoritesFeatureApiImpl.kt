package jobajob.feature.favorites.api

import androidx.fragment.app.Fragment
import jobajob.feature.favorites.presentation.navigation.FavoritesNavigationFragment

object FavoritesFeatureApiImpl : FavoritesFeatureApi {
    override fun getFavoritesFragment(): Fragment = FavoritesNavigationFragment()
}