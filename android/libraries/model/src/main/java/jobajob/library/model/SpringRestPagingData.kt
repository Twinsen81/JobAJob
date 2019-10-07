package jobajob.library.model

import com.google.gson.annotations.SerializedName

class SpringRestPagingData {

    @SerializedName("size")
    var size = 0

    @SerializedName("totalElements")
    var totalElements = 0

    @SerializedName("totalPages")
    var totalPages = 0

    @SerializedName("number")
    var number = 0
}