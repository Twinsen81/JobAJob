package jobajob.library.network.entity.spring

import com.google.gson.annotations.SerializedName

class SpringPagingDataDto {

    @SerializedName("size")
    var size = 0

    @SerializedName("totalElements")
    var totalElements = 0

    @SerializedName("totalPages")
    var totalPages = 0

    @SerializedName("number")
    var number = 0
}