package jobajob.feature.dashboard.presentation.vacancies

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jobajob.feature.dashboard.presentation.vacancies.adapter.header.HeaderItem
import jobajob.feature.dashboard.presentation.vacancies.adapter.vacancies.VacancyItem
import jobajob.feature.vacancies.entity.SalaryType
import jobajob.feature.vacancies.entity.Vacancy
import jobajob.feature.vacancies.usecase.GetVacanciesUseCase
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
internal class VacanciesViewModel @ViewModelInject constructor(
    private val getVacanciesUseCase: GetVacanciesUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val mutableState = MutableStateFlow(VacanciesViewState.emptyLoading)
    val state: StateFlow<VacanciesViewState> get() = mutableState

    private val mutableEvent = MutableSharedFlow<VacanciesViewModelEvent>()
    val event: SharedFlow<VacanciesViewModelEvent> get() = mutableEvent.asSharedFlow()

    init {
        loadVacancies()
    }

    private fun loadVacancies() {
        setLoadingState()
        viewModelScope.launch {
            val page = getVacanciesUseCase.getVacancies(null, null)
            if (page is Result.Success) {
                mutableState.value = mapSuccessToViewState(mutableState.value, page.value.data)
                mutableEvent.emit(VacanciesViewModelEvent.DisplayError("OKK"))
            } else {
                mutableState.value = mapErrorToViewState(mutableState.value, (page as Result.Error<Failure>).error)
            }
        }
    }

    private fun setLoadingState(isLoading: Boolean = true) {
        mutableState.value = mutableState.value.copy(loading = isLoading)
    }

    private fun mapSuccessToViewState(oldState: VacanciesViewState, vacancies: List<Vacancy>): VacanciesViewState {
        return VacanciesViewState(
            loading = false,
            header = HeaderItem("Vacancies"),
            vacancies = vacancies.map {
                VacancyItem(
                    id = it.id,
                    title = it.title,
                    city = it.city,
                    salary = getVacancySalary(it.salaryType, it.salaryMin, it.salaryMax),
                    employerName = "",
                    isFavorite = false
                )
            }
        )
    }

    private fun getVacancySalary(salaryType: SalaryType?, salaryMin: Int?, salaryMax: Int?): String? {
        return when {
            (salaryType == null || (salaryMin == null && salaryMax == null)) -> null
            salaryMin == null -> "up to $salaryMax"
            salaryMax == null -> "from $salaryMin"
            else -> "$salaryMin - $salaryMax (${salaryType})"
        }
    }


    private suspend fun mapErrorToViewState(oldState: VacanciesViewState, error: Failure): VacanciesViewState {
        setLoadingState(false)
        mutableEvent.emit(VacanciesViewModelEvent.DisplayError("Something went wrong"))
        return oldState.copy(loading = false)
    }
}
