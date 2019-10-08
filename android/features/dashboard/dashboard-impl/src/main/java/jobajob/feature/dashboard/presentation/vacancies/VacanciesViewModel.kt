package jobajob.feature.dashboard.presentation.vacancies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import jobajob.feature.dashboard.domain.intercator.GetVacanciesUseCase
import jobajob.library.entity.vacancy.Vacancy
import jobajob.library.uicomponents.presentation.BaseViewModel
import javax.inject.Inject
import jobajob.library.entity.common.Result

internal class VacanciesViewModel @Inject constructor(
    private val getVacanciesUseCase: GetVacanciesUseCase
) : BaseViewModel(getVacanciesUseCase) {

    private val _vacancies: MutableLiveData<List<Vacancy>> = MutableLiveData()
    val vacancies get() = _vacancies as LiveData<List<Vacancy>>

    fun loadVacancies() {
        getVacanciesUseCase(singleObserver { vacanciesListResult ->
            when (vacanciesListResult) {
                is Result.Success -> _vacancies.value = vacanciesListResult.value
                is Result.Error -> handleFailure(vacanciesListResult.error)
            }
        })
    }
}
