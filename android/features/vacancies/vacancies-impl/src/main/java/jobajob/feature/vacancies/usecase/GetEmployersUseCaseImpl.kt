package jobajob.feature.vacancies.usecase

import jobajob.feature.vacancies.network.VacanciesServerApi
import kotlinx.coroutines.CoroutineDispatcher

internal class GetEmployersUseCaseImpl(
    private val serverApi: VacanciesServerApi,
    private val dispatcher: CoroutineDispatcher,
) : GetEmployersUseCase {
    override suspend fun getEmployers(startAt: String?, number: Int?): EmployersPageResult {
        TODO("Not yet implemented")
    }
}