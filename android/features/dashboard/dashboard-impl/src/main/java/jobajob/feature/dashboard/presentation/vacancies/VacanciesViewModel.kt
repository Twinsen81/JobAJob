package jobajob.feature.dashboard.presentation.vacancies

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jobajob.feature.vacancies.usecase.GetVacanciesUseCase
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result
import jobajob.library.uicomponents.analytics.AnalyticsViewVacancyEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalCoroutinesApi
internal class VacanciesViewModel @ViewModelInject constructor(
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val viewStateMapper: VacanciesViewStateMapper,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val mutableState = MutableStateFlow(VacanciesViewState.emptyLoading)
    private val currentState: VacanciesViewState get() = mutableState.value
    val state: StateFlow<VacanciesViewState> get() = mutableState

    private val mutableEvent = MutableSharedFlow<VacanciesViewModelEvent>()

    val event: SharedFlow<VacanciesViewModelEvent> get() = mutableEvent.asSharedFlow()
    val action = MutableSharedFlow<VacanciesViewAction>()

    init {
        observeViewActions()
        loadVacancies()
    }

    private fun loadVacancies() {
        setLoadingState()
        viewModelScope.launch {
            val page = getVacanciesUseCase.getVacancies(null, null)
            if (page is Result.Success) {
                mutableState.value = viewStateMapper.map(currentState, page.value.data)
            } else {
                handleError((page as Result.Error<Failure>).error)
            }
        }
    }

    private suspend fun handleError(error: Failure) {
        mutableState.value = viewStateMapper.map(currentState, error)
        mutableEvent.emit(VacanciesViewModelEvent.DisplayError("Something went wrong"))
    }

    private fun setLoadingState() {
        mutableState.value = viewStateMapper.map(currentState, true)
    }

    private fun observeViewActions() {
        viewModelScope.launch {
            action
                .onEach { processAction(it) }
                .catch { Timber.e(it, "Error while processing an action from View") }
                .collect()
        }
    }

    private fun processAction(action: VacanciesViewAction) {
        viewModelScope.launch {
            return@launch when (action) {
                is VacanciesViewAction.VacancyClicked -> {
                    AnalyticsViewVacancyEvent.invoke(action.vacancyId)
                    mutableEvent.emit(VacanciesViewModelEvent.DisplayVacancy(action.vacancyId))
                }
            }
        }
    }
}
