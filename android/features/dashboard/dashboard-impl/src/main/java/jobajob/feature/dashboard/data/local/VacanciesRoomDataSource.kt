package jobajob.feature.dashboard.data.local

import io.reactivex.Single
import jobajob.feature.dashboard.data.local.dto.VacancyLocalDto
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result
import javax.inject.Inject

internal class VacanciesRoomDataSource @Inject constructor(): VacanciesLocalDataSource {
    override fun getVacancies(): Single<VacanciesLocalDtoListResult> {
        return Single.just(Result.Success(emptyList()))
    }
}

internal typealias VacanciesLocalDtoListResult = Result<List<VacancyLocalDto>, Failure>