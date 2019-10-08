package jobajob.feature.dashboard.data.remote

import io.reactivex.Single
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result
import jobajob.library.entity.vacancy.Vacancy

internal interface VacanciesRemoteDataSource {
    fun getVacancies(): Single<Result<List<Vacancy>, Failure>>
}