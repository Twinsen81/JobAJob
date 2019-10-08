package jobajob.feature.dashboard.data.local

import io.reactivex.Single
import jobajob.feature.dashboard.domain.gateway.VacanciesListResult
import jobajob.library.entity.common.Result
import javax.inject.Inject

internal class VacanciesRoomDataSource @Inject constructor(): VacanciesLocalDataSource {
    override fun getVacancies(): Single<VacanciesListResult> {
        return Single.just(Result.Success(emptyList()))
    }
}