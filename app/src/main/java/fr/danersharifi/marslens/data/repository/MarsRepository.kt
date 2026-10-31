package fr.danersharifi.marslens.data.repository

import fr.danersharifi.marslens.data.model.MarsPhotoPage
import fr.danersharifi.marslens.data.model.Mission
import fr.danersharifi.marslens.data.remote.MarsApiService
import fr.danersharifi.marslens.data.remote.MarsClient
import fr.danersharifi.marslens.data.model.MarsPhoto

class MarsRepository(
    private val api: MarsApiService = MarsClient.create(),
) {
    suspend fun getPhotos(
        mission: Mission,
        sol: Int,
        page: Int = 0,
    ): MarsPhotoPage {
        // Filters include the value and its NASA field name, as the example given by the prof on the white board
        val response = api.getPhotos(
            missionFilter = "${mission.apiValue}:mission",
            solFilter = "$sol:sol:in",
            page = page,
        )

        return MarsPhotoPage(
            photos = response.items.map { dto -> dto.toModel() },
            total = response.total,
            hasMore = response.more,
        )
    }

    suspend fun getPhoto(
        photoId: Int,
    ): MarsPhoto? {
        val response = api.getPhoto(
            photoIdFilter = "$photoId:id",
        )

        // the API returns a list of ONE so find the matching ID by precaution, null if missing
        return response.items
            .firstOrNull { dto -> dto.id == photoId }
            ?.toModel()
    }
}