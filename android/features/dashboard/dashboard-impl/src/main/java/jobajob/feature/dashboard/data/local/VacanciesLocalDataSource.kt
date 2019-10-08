package jobajob.feature.dashboard.data.local

import io.reactivex.Single
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result
import jobajob.library.entity.vacancy.Vacancy

internal interface VacanciesLocalDataSource {
    fun getVacancies(): Single<Result<List<Vacancy>, Failure>>
}