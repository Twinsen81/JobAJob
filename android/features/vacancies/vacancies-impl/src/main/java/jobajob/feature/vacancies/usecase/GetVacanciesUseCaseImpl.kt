package jobajob.feature.vacancies.usecase

import jobajob.feature.vacancies.network.VacanciesServerApi
import kotlinx.coroutines.CoroutineDispatcher

internal class GetVacanciesUseCaseImpl(
    private val serverApi: VacanciesServerApi,
    private val dispatcher: CoroutineDispatcher,
) : GetVacanciesUseCase {
    override suspend fun getVacancies(startAt: String?, number: Int?): VacanciesPageResult {
        TODO("Not yet implemented")
    }
}