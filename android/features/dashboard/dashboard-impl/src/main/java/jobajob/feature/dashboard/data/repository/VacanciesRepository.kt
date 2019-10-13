package jobajob.feature.dashboard.data.repository

import io.reactivex.Single
import jobajob.feature.dashboard.data.local.VacanciesLocalDataSource
import jobajob.feature.dashboard.data.remote.VacanciesRemoteDataSource
import jobajob.feature.dashboard.data.remote.VacanciesRemoteDtoListResult
import jobajob.feature.dashboard.domain.gateway.VacanciesGateway
import jobajob.feature.dashboard.domain.gateway.VacanciesListResult
import jobajob.library.entity.vacancy.Vacancy
import javax.inject.Inject

internal class VacanciesRepository @Inject constructor(
    private val localDataSource: VacanciesLocalDataSource,
    private val remoteDataSource: VacanciesRemoteDataSource
) : VacanciesGateway {
    override fun getVacancies(page: Int): Single<VacanciesListResult> {
        return remoteDataSource.getVacancies(page)
            .map { remoteResult -> mapRemoteToDomain(remoteResult) }
    }

    private fun mapRemoteToDomain(remoteResult: VacanciesRemoteDtoListResult): VacanciesListResult =
        remoteResult.mapSuccess { list -> list.map {
            Vacancy(it.id, it.title, "", it.details, it.salary, it.payed)
        } }
}