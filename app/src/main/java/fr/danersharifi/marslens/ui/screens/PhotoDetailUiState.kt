package fr.danersharifi.marslens.ui.screens

import fr.danersharifi.marslens.data.model.MarsPhoto

// Séperate missing & error states
data class PhotoDetailUiState(
    val photo: MarsPhoto? = null,
    val isLoading: Boolean = true,
    val hasError: Boolean = false,
)