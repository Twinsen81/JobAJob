package jobajob.feature.dashboard.presentation.vacancydetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

internal class VacancyDetailViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel()