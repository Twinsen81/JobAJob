package com.evartem.jobajob.data

import com.evartem.jobajob.domain.Vacancy
import org.springframework.data.repository.PagingAndSortingRepository

@Suppress("unused")
interface VacancyRepository : PagingAndSortingRepository<Vacancy, Long>
