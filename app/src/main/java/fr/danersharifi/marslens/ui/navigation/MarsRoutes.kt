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