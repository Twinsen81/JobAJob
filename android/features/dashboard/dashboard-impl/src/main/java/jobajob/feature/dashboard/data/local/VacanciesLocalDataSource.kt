package jobajob.feature.dashboard.data.local

import io.reactivex.Single
import jobajob.feature.dashboard.domain.gateway.VacanciesListResult

internal interface VacanciesLocalDataSource {
    fun getVacancies(): Single<VacanciesLocalDtoListResult>
}