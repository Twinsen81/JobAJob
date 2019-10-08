package jobajob.feature.dashboard.data.remote.entity

import com.google.gson.annotations.SerializedName
import jobajob.library.network.entity.spring.SpringRestResponse
import jobajob.library.entity.vacancy.Vacancy

internal class VacanciesResponse(
    @SerializedName("_embedded")
    val embedded: Embedded
): SpringRestResponse() {

    inner class Embedded(
        @SerializedName("vacancies")
        var data: List<Vacancy> = emptyList()
    )
}