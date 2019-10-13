package jobajob.feature.dashboard.data.remote

import io.reactivex.Single
import jobajob.feature.dashboard.data.remote.dto.VacancyRemoteDto
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result

internal interface VacanciesRemoteDataSource {
    fun getVacancies(page: Int): Single<VacanciesRemoteDtoListResult>
}

internal typealias VacanciesRemoteDtoListResult = Result<List<VacancyRemoteDto>, Failure>