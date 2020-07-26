package jobajob.feature.favorites.presentation.navigation

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import jobajob.feature.favorites.presentation.favorites.FavoritesFragment
import jobajob.library.uicomponents.presentation.FeatureNavigationHostFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

@AndroidEntryPoint
internal class FavoritesNavigationFragment : FeatureNavigationHostFragment() {

    override fun getStartScreen(): SupportAppScreen =
        object : SupportAppScreen() {
            override fun getFragment(): Fragment =
                FavoritesFragment()
        }
}