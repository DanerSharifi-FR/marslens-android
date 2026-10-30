package fr.danersharifi.marslens.data.remote.dto

import kotlinx.serialization.Serializable

// Photos and pagination metadata
@Serializable
data class MarsPhotoResponseDto(
    val items: List<MarsPhotoDto>,
    val total: Int,
    val more: Boolean,
)