package jobajob.library.network.dto.spring

import com.google.gson.annotations.SerializedName

/**
 * A base class for Spring REST entities that return id's as links
 */
open class SpringEntityDto {

    @SerializedName("_links")
    var links: SpringLinksDto? = null

    inner class SpringLinksDto {
        @SerializedName("self")
        var self: SpringSelfDto? = null
    }

    inner class SpringSelfDto {
        @SerializedName("href")
        var href: String = ""
    }

    val id: Long
    get() =
        (links?.self?.href?.substringAfterLast('/', "0") ?: "0")
            .toLongOrNull() ?: 0

}