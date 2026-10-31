package fr.danersharifi.marslens.data.model

// Keep API-specific field names in the DTO
data class MarsPhoto(
    val id: Int,
    val sol: Int,
    val mission: String,
    val title: String,
    val camera: String,
    val imageUrl: String,
    val dateTaken: String?,
    val description: String?,
    val imageCredit: String?,
)
