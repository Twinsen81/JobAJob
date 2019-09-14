package com.evartem.jobajob.domain

import javax.persistence.*

@Entity
class EmployerProfile(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        var companyName: String = "",
        var companyDescription: String = "",
        var companyLocation: String = "",
        var companyLogoUrl: String = "",
        var contactName: String = "",
        var contactEmail: String = "",
        @OneToMany(cascade = [CascadeType.ALL])
        var vacancies: List<Vacancy> = emptyList(),
        @OneToMany(cascade = [CascadeType.ALL])
        var savedResumes: List<Resume> = emptyList()
)
