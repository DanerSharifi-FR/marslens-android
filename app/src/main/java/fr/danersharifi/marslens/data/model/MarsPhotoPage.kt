package fr.danersharifi.marslens.data.model

data class MarsPhotoPage(
    val photos: List<MarsPhoto>,
    val total: Int,
    val hasMore: Boolean,
)
