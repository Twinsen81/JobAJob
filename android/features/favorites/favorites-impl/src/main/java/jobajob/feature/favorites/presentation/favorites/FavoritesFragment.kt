package jobajob.feature.favorites.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jobajob.feature.favorites.R
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlin.random.Random

@AndroidEntryPoint
internal class FavoritesFragment : Fragment() {

    private val viewModel: FavoritesViewModel by viewModels()

    private val rnd = Random.nextInt()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favorites, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbTitle.text = "FavID:  $rnd"
    }
}