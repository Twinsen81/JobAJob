package com.evartem.jobajob.data

import com.evartem.jobajob.domain.EmployerProfile
import org.springframework.data.repository.PagingAndSortingRepository

@Suppress("unused")
interface EmployerProfileRepository : PagingAndSortingRepository<EmployerProfile, Long>
