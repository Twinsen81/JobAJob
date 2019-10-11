package jobajob.library.entity.vacancy

data class Vacancy(
    val id: Long,
    val title: String,
    val company: String,
    val details: String,
    val salary: String,
    val payed: Boolean
)