package fr.danersharifi.marslens.ui.screens

import fr.danersharifi.marslens.data.model.MarsPhoto

// keep results, loading & errors together (I do this at my apprenticeship level, maybe a best practice)
data class PhotosUiState(
    val photos: List<MarsPhoto> = emptyList(),
    val isLoading: Boolean = true,
    val hasError: Boolean = false,
    val total: Int = 0,
    val hasMore: Boolean = false,
    val isLoadingMore: Boolean = false,
    val hasLoadMoreError: Boolean = false,
)