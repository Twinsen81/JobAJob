package jobajob.feature.dashboard.presentation.vacancies

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import jobajob.feature.dashboard.domain.intercator.GetVacanciesUseCase
import jobajob.library.entity.vacancy.Vacancy
import jobajob.library.uicomponents.presentation.BaseViewModel
import javax.inject.Inject
import androidx.paging.LivePagedListBuilder


internal class VacanciesViewModel @Inject constructor(
    getVacanciesUseCase: GetVacanciesUseCase
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
