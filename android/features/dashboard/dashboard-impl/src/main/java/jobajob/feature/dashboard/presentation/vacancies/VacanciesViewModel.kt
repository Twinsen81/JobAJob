package jobajob.feature.dashboard.presentation.vacancies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
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

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(10)
        .setPageSize(20)
        .build()
    //val noteList = LivePagedListBuilder<Int, Vacancy>(getNotesUseCase.allNotes(), pagedListConfig).build()

    init {

    }

    fun loadVacancies() {
        getVacanciesUseCase(
            singleObserver { result ->
                result.either(
                    { list -> _vacancies.value = list },
                    { failure -> handleFailure(failure) })
            }, GetVacanciesUseCase.Params(0))
    }
}
