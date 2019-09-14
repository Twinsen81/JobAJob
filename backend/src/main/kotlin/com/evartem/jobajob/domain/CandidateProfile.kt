package com.evartem.jobajob.domain

import javax.persistence.*

@Entity
@Table(name = "candidateprofile")
class CandidateProfile(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        var firstName: String = "",
        var lastName: String = "",
        var email: String = "",
        var phone: String = "",
        var photoUrl: String = "",
        @OneToMany(cascade = [CascadeType.ALL])
        var resumes: List<Resume> = emptyList(),
        @OneToMany(cascade = [CascadeType.ALL])
        var savedVacancies: List<Vacancy> = emptyList()
)
