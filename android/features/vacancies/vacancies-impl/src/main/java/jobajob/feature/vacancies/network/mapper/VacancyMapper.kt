package jobajob.feature.vacancies.network.mapper

import jobajob.feature.vacancies.entity.*
import jobajob.feature.vacancies.network.dto.VacancyDto
import jobajob.library.network.utils.Mapper
import javax.inject.Inject

internal class VacancyMapper @Inject constructor() : Mapper<VacancyDto, Vacancy> {
    override fun map(input: VacancyDto): Vacancy {
        return Vacancy(
            id = input.id!!,
            title = input.title,
            description = input.description,
            industry = Industry.fromString(input.industry),
            date = input.date,
            salaryMin = input.salaryMin,
            salaryMax = input.salaryMax,
            salaryType = SalaryType.fromString(input.salaryType),
            city = input.city,
            employer = EmployerShort(
                id = input.employerId,
                name = input.employerName,
                logoUrl = input.employerLogoUrl,
            ),
            experience = RequiredExperience.fromString(input.experience),
            schedule = WorkSchedule.fromString(input.schedule),
            skills = input.skills.toList(),
        )
    }
}