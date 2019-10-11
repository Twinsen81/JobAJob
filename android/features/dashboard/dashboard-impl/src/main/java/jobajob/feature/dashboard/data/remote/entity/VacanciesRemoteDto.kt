package jobajob.feature.dashboard.data.remote.entity

import com.google.gson.annotations.SerializedName
import jobajob.library.network.entity.spring.SpringPagingDto
import jobajob.library.entity.vacancy.Vacancy

internal class VacanciesRemoteDto(
    @SerializedName("_embedded")
    val embedded: Embedded
): SpringPagingDto() {

    inner class Embedded(
        @SerializedName("vacancies")
        var data: List<Vacancy> = emptyList()
    )
}