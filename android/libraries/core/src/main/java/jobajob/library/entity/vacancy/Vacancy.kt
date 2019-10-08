package jobajob.library.entity.vacancy

data class Vacancy(
    val id: Long = 0,
    val title: String = "",
    val details: String = "",
    val salary: String = "",
    val payed: Boolean = false
)