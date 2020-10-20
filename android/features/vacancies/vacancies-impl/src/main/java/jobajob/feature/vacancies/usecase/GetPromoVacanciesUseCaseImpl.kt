package jobajob.feature.vacancies.usecase

import jobajob.feature.vacancies.entity.DataPage
import jobajob.feature.vacancies.entity.PromoVacancy
import jobajob.feature.vacancies.network.VacanciesServerApi
import jobajob.feature.vacancies.network.dto.PromoVacancyDto
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result
import jobajob.library.network.utils.Mapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetPromoVacanciesUseCaseImpl(
    private val serverApi: VacanciesServerApi,
    private val vacancyMapper: Mapper<PromoVacancyDto, PromoVacancy>,
    private val dispatcher: CoroutineDispatcher,
) : GetPromoVacanciesUseCase {
    override suspend fun getPromoVacancies(): PromoVacanciesPageResult {
        return withContext(dispatcher) {
            try {
                val vacanciesList = serverApi.getPromoVacancies()
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
}