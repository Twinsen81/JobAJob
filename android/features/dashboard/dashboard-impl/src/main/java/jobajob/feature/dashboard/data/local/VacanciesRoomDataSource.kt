package jobajob.feature.dashboard.data.local

import io.reactivex.Single
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result
import jobajob.library.entity.vacancy.Vacancy
import javax.inject.Inject

internal class VacanciesRoomDataSource @Inject constructor(): VacanciesLocalDataSource {
    override fun getVacancies(): Single<Result<List<Vacancy>, Failure>> {
        return Single.just(Result.Success(emptyList()))
    }
}