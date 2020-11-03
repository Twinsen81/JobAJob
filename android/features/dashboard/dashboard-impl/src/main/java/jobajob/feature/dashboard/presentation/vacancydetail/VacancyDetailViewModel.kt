package jobajob.feature.dashboard.presentation.vacancydetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jobajob.feature.vacancies.usecase.GetVacanciesUseCase
import jobajob.library.entity.common.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class VacancyDetailViewModel @ViewModelInject constructor(
    private val getVacanciesUseCase: GetVacanciesUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val mutableState = MutableStateFlow<VacancyDetailViewState>(VacancyDetailViewState.Loading)
    val state: StateFlow<VacancyDetailViewState> get() = mutableState

    private lateinit var vacancyId: String

    fun init(vacancyId: String) {
        this.vacancyId = vacancyId
        loadVacancy()
    }

    private fun loadVacancy() {
        viewModelScope.launch {
            val result = getVacanciesUseCase.getVacancy(vacancyId)
            if (result is Result.Success) {
                mutableState.value = VacancyDetailViewState.Data(result.value, false)
            } else {
                mutableState.value = VacancyDetailViewState.Error("Something went wrong")
            }
        }
    }
}