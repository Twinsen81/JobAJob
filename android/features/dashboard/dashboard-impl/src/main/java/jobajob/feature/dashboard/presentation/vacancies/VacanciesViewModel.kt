package jobajob.feature.dashboard.presentation.vacancies

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import jobajob.feature.vacancies.entity.DataPage
import jobajob.feature.vacancies.entity.Vacancy
import jobajob.feature.vacancies.usecase.GetVacanciesUseCase
import jobajob.library.entity.common.Result


internal class VacanciesViewModel @ViewModelInject constructor(
    getVacanciesUseCase: GetVacanciesUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
/*
    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(20)
        .setPageSize(20)
        .build()

    val vacancies: LiveData<PagedList<Vacancy>> =
        LivePagedListBuilder(
            VacanciesPagedDataSourceFactory(
                getVacanciesUseCase
            ), pagedListConfig)
            .build()*/

    val vacancies = liveData {
        val page = getVacanciesUseCase.getVacancies(null, null)
        if (page is Result.Success) {
            emit(page.value)
        } else {
            emit(DataPage<Vacancy>(emptyList(), null))
        }
    }
}
