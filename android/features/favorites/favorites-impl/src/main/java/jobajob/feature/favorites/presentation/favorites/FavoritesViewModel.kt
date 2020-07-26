package jobajob.feature.favorites.presentation.favorites

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

internal class FavoritesViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel()
