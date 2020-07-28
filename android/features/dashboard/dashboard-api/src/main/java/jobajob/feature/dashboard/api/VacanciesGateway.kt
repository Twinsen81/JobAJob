package jobajob.feature.dashboard.api

import io.reactivex.Single
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result
import jobajob.library.entity.vacancy.Vacancy

interface VacanciesGateway {
    fun getVacancies(page: Int): Single<VacanciesListResult>
}

typealias VacanciesListResult = Result<List<Vacancy>, Failure>