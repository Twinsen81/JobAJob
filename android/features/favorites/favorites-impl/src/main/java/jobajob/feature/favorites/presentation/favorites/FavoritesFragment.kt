package jobajob.feature.favorites.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import jobajob.feature.favorites.R
import jobajob.feature.favorites.di.FavoritesFeatureComponent
import jobajob.library.uicomponents.presentation.BaseNavigationFragment
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlin.random.Random

internal class FavoritesFragment : BaseNavigationFragment() {

    private lateinit var viewModel: FavoritesViewModel

    private val rnd = Random.nextInt()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favorites, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FavoritesFeatureComponent.get().inject(this)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(FavoritesViewModel::class.java)

        dbTitle.text = "${dbTitle.text}  $rnd"
    }
}