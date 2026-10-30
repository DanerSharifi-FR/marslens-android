package fr.danersharifi.marslens.data.remote.dto

import fr.danersharifi.marslens.data.model.MarsPhoto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarsPhotoDto(
    val id: Int,
    val sol: Int,
    val mission: String,
    val instrument: String? = null,
    val title: String? = null,

    // API field names map to Kotlin names
    @SerialName(value = "https_url")
    val httpsUrl: String? = null,

    val url: String? = null,

    @SerialName(value = "date_taken")
    val dateTaken: String? = null,

    val description: String? = null,

    @SerialName(value = "image_credit")
    val imageCredit: String? = null,
) {
    fun toModel(): MarsPhoto {
        // prioritize HTTPS :D
        val imageUrl = (httpsUrl?.takeIf { it.isNotBlank() } ?: url.orEmpty())
            .replaceFirst(
                oldValue = "http://",
                newValue = "https://",
            )

        return MarsPhoto(
            id = id,
            sol = sol,
            mission = mission,
            title = title.orEmpty(),
            camera = instrument.orEmpty(),
            imageUrl = imageUrl,
            dateTaken = dateTaken,
            description = description,
            imageCredit = imageCredit,
        )
    }
}
