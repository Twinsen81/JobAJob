package jobajob.feature.dashboard.data.remote

import io.reactivex.Single
import jobajob.feature.dashboard.data.remote.api.DashboardApi
import jobajob.feature.dashboard.data.remote.entity.VacanciesRemoteDto
import jobajob.feature.dashboard.domain.gateway.VacanciesListResult
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result
import jobajob.library.network.utils.toResult
import javax.inject.Inject

internal class VacanciesRetrofitDataSource @Inject constructor(private val api: DashboardApi) :
    VacanciesRemoteDataSource {
    override fun getVacancies(): Single<VacanciesListResult> {
        return try {
            api.fetchVacancies()
                .map { response -> mapSpringResultToData(response.toResult()) }
        } catch (e: Throwable) {
            Single.just(Result.Error(Failure.ApplicationError(e)))
        }
    }

    private fun mapSpringResultToData(result: Result<VacanciesRemoteDto, Failure>): VacanciesListResult =
        when (result) {
            is Result.Success -> Result.Success(result.value.embedded.data)
            is Result.Error -> Result.Error(result.error)
        }
}