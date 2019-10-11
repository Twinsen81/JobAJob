package jobajob.library.network.dto.spring

import com.google.gson.annotations.SerializedName

/**
 * A base class for Spring REST responses containing pagination data
 */
open class SpringPagingDto {

    @SerializedName("page")
    var pagingData: SpringPagingDataDto? = null

    inner class SpringPagingDataDto {

        @SerializedName("size")
        var size = 0

        @SerializedName("totalElements")
        var totalElements = 0

        @SerializedName("totalPages")
        var totalPages = 0

        @SerializedName("number")
        var number = 0
    }
}