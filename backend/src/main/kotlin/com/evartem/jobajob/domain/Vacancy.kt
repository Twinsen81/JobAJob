package com.evartem.jobajob.domain

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "vacancy")
data class Vacancy(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        var title: String = "",
        var details: String = "",
        var salary: String = "",
        var payed: Boolean = false
)
