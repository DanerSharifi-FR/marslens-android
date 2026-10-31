package fr.danersharifi.marslens.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object SearchRoute

// passe search criteria not the photos themselves
@Serializable
data class PhotosRoute(
    val missionCode: String,
    val sol: Int,
)

// Pass the ID so the detail screen can fetch its own data
@Serializable
data class PhotoDetailRoute(val photoId: Int)
