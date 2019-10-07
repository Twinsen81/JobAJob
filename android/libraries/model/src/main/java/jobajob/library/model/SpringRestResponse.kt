package jobajob.library.model

import com.google.gson.annotations.SerializedName

class SpringRestResponse {

    @SerializedName("page")
    var pagingData: SpringRestPagingData? = null
}