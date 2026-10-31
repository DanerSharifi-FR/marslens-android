package fr.danersharifi.marslens.data.remote

import fr.danersharifi.marslens.data.remote.dto.MarsPhotoResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsApiService {
    @GET(value = "v1/raw_image_items/")
    suspend fun getPhotos(
        // example prof on white board: "msl:mission"
        @Query(value = "condition_1")
        missionFilter: String,

        // example prof on white board: "123:sol:in"
        @Query(value = "condition_2")
        solFilter: String,

        @Query(value = "page")
        page: Int = 0,

        @Query(value = "per_page")
        perPage: Int = 25,

        @Query(value = "order")
        order: String = "id asc",
    ): MarsPhotoResponseDto
}
