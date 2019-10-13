package jobajob.feature.dashboard.domain.gateway

import io.reactivex.Single
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result
import jobajob.library.entity.vacancy.Vacancy

internal interface VacanciesGateway {
    fun getVacancies(page: Int): Single<VacanciesListResult>
}

internal typealias VacanciesListResult = Result<List<Vacancy>, Failure>