package jobajob.feature.dashboard.data.remote.dto

import com.google.gson.annotations.SerializedName
import jobajob.library.network.dto.spring.SpringEntityDto

internal data class VacancyRemoteDto(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("details")
    val details: String = "",
    @SerializedName("salary")
    val salary: String = "",
    @SerializedName("payed")
    val payed: Boolean = false
) : SpringEntityDto()