package com.evartem.jobajob.data

import com.evartem.jobajob.domain.CandidateProfile
import org.springframework.data.repository.PagingAndSortingRepository

@Suppress("unused")
interface CandidateProfileRepository : PagingAndSortingRepository<CandidateProfile, Long>
