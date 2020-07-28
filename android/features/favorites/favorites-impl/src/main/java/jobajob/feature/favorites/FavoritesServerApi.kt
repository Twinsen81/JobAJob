package jobajob.feature.favorites

import io.reactivex.Completable
import retrofit2.http.GET
import retrofit2.http.Query

internal interface FavoritesServerApi {

    @GET("favorites")
    fun fetchFavorites(@Query("page") page: Int): Completable
}
