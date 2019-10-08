package jobajob.feature.dashboard.data.remote

import io.reactivex.Single
import jobajob.feature.dashboard.data.remote.api.DashboardApi
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result
import jobajob.library.entity.vacancy.Vacancy
import javax.inject.Inject

internal class VacanciesRetrofitDataSource @Inject constructor(private val api: DashboardApi): VacanciesRemoteDataSource {
    override fun getVacancies(): Single<Result<List<Vacancy>, Failure>> {
        return try {
            api.fetchVacancies()
                .map { response -> Result.Success(response.body()!!.embedded.data) }
        } catch (e: Throwable) {
            Single.just(Result.Error(Failure.NetworkConnection(e)))
        }
    }
}