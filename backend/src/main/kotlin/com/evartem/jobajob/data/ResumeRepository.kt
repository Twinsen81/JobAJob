package com.evartem.jobajob.data

import com.evartem.jobajob.domain.Resume
import org.springframework.data.repository.PagingAndSortingRepository

@Suppress("unused")
interface ResumeRepository : PagingAndSortingRepository<Resume, Long>
