package jobajob.feature.vacancies.network.mapper

import jobajob.feature.vacancies.entity.EmployerShort
import jobajob.feature.vacancies.entity.PromoVacancy
import jobajob.feature.vacancies.network.dto.PromoVacancyDto
import jobajob.library.network.utils.Mapper
import javax.inject.Inject

internal class PromoVacancyMapper @Inject constructor() : Mapper<PromoVacancyDto, PromoVacancy> {
    override fun map(input: PromoVacancyDto): PromoVacancy {
        return PromoVacancy(
            id = input.id!!,
            title = input.title,
            employer = EmployerShort(
                id = input.employerId,
                name = input.employerName,
                logoUrl = input.employerLogoUrl,
            ),
        )
    }
}