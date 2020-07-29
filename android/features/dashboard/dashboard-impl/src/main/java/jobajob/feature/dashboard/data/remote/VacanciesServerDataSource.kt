package jobajob.feature.dashboard.data.remote

import io.reactivex.Single
import jobajob.feature.dashboard.data.remote.api.DashboardServerApi
import jobajob.feature.dashboard.data.remote.dto.VacanciesRemoteDto
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result
import jobajob.library.network.utils.toResult
import javax.inject.Inject

internal class VacanciesServerDataSource @Inject constructor(private val serverApi: DashboardServerApi) :
    VacanciesRemoteDataSource {
    override fun getVacancies(page: Int): Single<VacanciesRemoteDtoListResult> {
        return try {
            serverApi.fetchVacancies(page)
                .map { response -> mapSpringResultToData(response.toResult()) }
        } catch (e: Throwable) {
            Single.just(Result.Error(Failure.ApplicationError(e)))
        }
    }

    private fun mapSpringResultToData(result: Result<VacanciesRemoteDto, Failure>): VacanciesRemoteDtoListResult =
        result.mapSuccess { remoteDto -> remoteDto.embedded.data}
}