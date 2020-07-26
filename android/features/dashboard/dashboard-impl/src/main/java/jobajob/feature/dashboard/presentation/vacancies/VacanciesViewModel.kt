package jobajob.feature.dashboard.presentation.vacancies

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import jobajob.feature.dashboard.domain.intercator.GetVacanciesUseCase
import jobajob.library.entity.vacancy.Vacancy
import jobajob.library.uicomponents.presentation.BaseViewModel

internal class VacanciesViewModel @ViewModelInject constructor(
    getVacanciesUseCase: GetVacanciesUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel(getVacanciesUseCase) {

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
            .build()
}
