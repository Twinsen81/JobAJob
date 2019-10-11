package jobajob.library.network.entity.spring

import com.google.gson.annotations.SerializedName

open class SpringPagingDto {

    @SerializedName("page")
    var pagingData: SpringPagingDataDto? = null
}