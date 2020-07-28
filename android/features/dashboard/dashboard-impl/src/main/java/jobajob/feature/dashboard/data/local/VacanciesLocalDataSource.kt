package jobajob.feature.dashboard.data.local

import io.reactivex.Single

internal interface VacanciesLocalDataSource {
    fun getVacancies(): Single<VacanciesLocalDtoListResult>
}