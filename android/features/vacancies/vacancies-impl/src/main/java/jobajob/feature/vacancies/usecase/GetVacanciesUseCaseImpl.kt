package jobajob.feature.vacancies.usecase

import jobajob.feature.vacancies.entity.EmployerLocation
import jobajob.feature.vacancies.entity.EmployerShort
import jobajob.feature.vacancies.entity.VacanciesPage
import jobajob.feature.vacancies.entity.Vacancy
import jobajob.feature.vacancies.network.VacanciesServerApi
import jobajob.feature.vacancies.usecase.dto.VacancyDto
import jobajob.library.entity.common.Failure
import jobajob.library.entity.common.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class GetVacanciesUseCaseImpl(
    private val serverApi: VacanciesServerApi,
    private val dispatcher: CoroutineDispatcher,
) : GetVacanciesUseCase {
    override suspend fun getVacancies(startAt: String?, number: Int?): VacanciesPageResult {
        return withContext(dispatcher) {
            try {
                val vacanciesList = serverApi.getVacancies()
                    .entries
                    .map { (id, dto) -> mapVacanciesDtoToDomain(id, dto) }

                Result.Success(
                    VacanciesPage(
                        vacancies = vacanciesList,
                        nextStartsAt = null
                    )
                )
            } catch (e: Throwable) {
                Result.Error(Failure.ServerError(e))
            }
        }
    }

    private fun mapVacanciesDtoToDomain(vacancyId: String, dto: VacancyDto): Vacancy {
        return Vacancy(
            id = vacancyId,
            title = dto.title,
            industry = dto.industry,
            date = dto.date,
            salaryMin = dto.salaryMin,
            salaryMax = dto.salaryMax,
            salaryType = dto.salaryType,
            location = EmployerLocation(
                city = dto.location.city,
                address = dto.location.address,
                latitude = dto.location.latitude,
                longitude = dto.location.longitude
            ),
            employer = EmployerShort(
                id = dto.employer.id,
                name = dto.employer.name,
                logoUrl = dto.employer.logoUrl
            ),
            experience = dto.experience,
            schedule = dto.schedule,
            skills = dto.skills
        )
    }
}