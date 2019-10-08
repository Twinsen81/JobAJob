package jobajob.library.entity.spring

import com.google.gson.annotations.SerializedName

open class SpringRestResponse {

    @SerializedName("page")
    var pagingData: SpringRestPagingData? = null
}