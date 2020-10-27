package jobajob.feature.vacancies.usecase

import jobajob.feature.vacancies.entity.DataPage
import jobajob.feature.vacancies.entity.Vacancy
import jobajob.feature.vacancies.network.VacanciesServerApi
import jobajob.feature.vacancies.network.dto.VacancyDto
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result
import jobajob.library.network.utils.Mapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetVacanciesUseCaseImpl(
    private val serverApi: VacanciesServerApi,
    private val vacancyMapper: Mapper<VacancyDto, Vacancy>,
    private val dispatcher: CoroutineDispatcher,
) : GetVacanciesUseCase {
    override suspend fun getVacancies(startAt: String?, number: Int?): VacanciesPageResult {
        return withContext(dispatcher) {
            try {
                val vacanciesList = serverApi.getVacancies()
                    .entries
                    .map { (vacancyId, dto) -> vacancyMapper.map(dto.apply { id = vacancyId }) }

                Result.Success(
                    DataPage(
                        data = vacanciesList,
                        nextStartsAt = null
                    )
                )
            } catch (e: Throwable) {
                Result.Error(Failure.ServerError(e))
            }
        }
    }

    override suspend fun getVacancy(vacancyId: String): VacancyResult {
        return withContext(dispatcher) {
            try {
                val vacancyDto = serverApi.getVacancy(vacancyId)
                val vacancy = vacancyMapper.map(vacancyDto.apply { id = vacancyId })
                Result.Success(vacancy)
            } catch (e: Throwable) {
                Result.Error(Failure.ServerError(e))
            }
        }
    }
}