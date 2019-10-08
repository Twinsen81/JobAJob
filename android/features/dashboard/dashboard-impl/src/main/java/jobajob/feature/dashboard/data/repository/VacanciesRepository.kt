package jobajob.feature.dashboard.data.repository

import io.reactivex.Single
import jobajob.feature.dashboard.data.local.VacanciesLocalDataSource
import jobajob.feature.dashboard.data.remote.VacanciesRemoteDataSource
import jobajob.feature.dashboard.domain.gateway.VacanciesGateway
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result
import jobajob.library.entity.vacancy.Vacancy
import javax.inject.Inject

internal class VacanciesRepository @Inject constructor(
    private val localDataSource: VacanciesLocalDataSource,
    private val remoteDataSource: VacanciesRemoteDataSource
): VacanciesGateway {
    override fun getVacancies(): Single<Result<List<Vacancy>, Failure>> {
        return remoteDataSource.getVacancies()
    }
}