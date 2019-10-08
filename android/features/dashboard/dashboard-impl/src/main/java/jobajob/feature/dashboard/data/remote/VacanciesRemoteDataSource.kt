package jobajob.feature.dashboard.data.remote

import io.reactivex.Single
import jobajob.feature.dashboard.domain.gateway.VacanciesListResult

internal interface VacanciesRemoteDataSource {
    fun getVacancies(): Single<VacanciesListResult>
}