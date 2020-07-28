package jobajob.feature.favorites.api

import androidx.fragment.app.Fragment
import jobajob.feature.favorites.presentation.navigation.FavoritesNavigationFragment
import javax.inject.Inject

class FavoritesFeatureApiImpl @Inject constructor() : FavoritesFeatureApi {
    override fun getFavoritesFragment(): Fragment = FavoritesNavigationFragment()
}