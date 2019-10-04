package jobajob.feature.favorites.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import jobajob.feature.favorites.R
import jobajob.feature.favorites.di.FavoritesFeatureComponent
import jobajob.library.uicomponents.navigation.BaseFeatureFragment
import kotlinx.android.synthetic.main.fragment_favorites.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import kotlin.random.Random

internal class FavoritesFragment : BaseFeatureFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: FavoritesViewModel

    @Inject
    lateinit var featureRouter: Router

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