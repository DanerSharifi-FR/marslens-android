package fr.danersharifi.marslens.data.remote.dto

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
)