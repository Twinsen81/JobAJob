package jobajob.feature.dashboard.data.remote

import io.reactivex.Single
import jobajob.feature.dashboard.data.remote.api.DashboardApi
import jobajob.feature.dashboard.data.remote.entity.VacanciesResponse
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
                .map { response -> mapSpringResponseToData(response.toResult()) }
        } catch (e: Throwable) {
            Single.just(Result.Error(Failure.NetworkConnection(e)))
        }
    }

    private fun mapSpringResponseToData(response: Result<VacanciesResponse, Failure>): VacanciesListResult =
        when (response) {
            is Result.Success -> Result.Success(response.value.embedded.data)
            is Result.Error -> Result.Error(response.error)
        }
}