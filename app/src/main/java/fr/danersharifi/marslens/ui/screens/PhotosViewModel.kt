package fr.danersharifi.marslens.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.danersharifi.marslens.data.model.Mission
import fr.danersharifi.marslens.data.repository.MarsRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PhotosViewModel(
    private val mission: Mission,
    private val sol: Int,
    private val repository: MarsRepository = MarsRepository(),
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        value = PhotosUiState(),
    )
    val uiState = _uiState.asStateFlow()

    private var loadJob: Job? = null
    private var nextPage: Int = 0

    init {
        loadPhotos()
    }

    fun loadPhotos() {
        // Avoid duplicate requests from repeated taps.
        if (loadJob?.isActive == true) return

        _uiState.value = PhotosUiState()
        loadPage(page = 0)
    }

    fun loadMore() {
        val currentState = _uiState.value

        if (loadJob?.isActive == true || !currentState.hasMore) return

        // Keep existing photos visible while fetching the next page.
        _uiState.value = currentState.copy(
            isLoadingMore = true,
            hasLoadMoreError = false,
        )

        loadPage(page = nextPage)
    }

    private fun loadPage(page: Int) {
        loadJob = viewModelScope.launch {
            try {
                val result = repository.getPhotos(
                    mission = mission,
                    sol = sol,
                    page = page,
                )

                val previousPhotos = if (page == 0) {
                    emptyList()
                } else {
                    _uiState.value.photos
                }

                nextPage = page + 1

                // disctinctBy prevents duplicate photo IDs between pages
                _uiState.value = PhotosUiState(
                    photos = (previousPhotos + result.photos)
                        .distinctBy { photo -> photo.id },
                    isLoading = false,
                    total = result.total,
                    hasMore = result.hasMore,
                )
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                Log.e(TAG, "Photo request failed (page $page)", exception)

                // bug seen: failed next page shouldn't erase existing photos
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoadingMore = false,
                    hasError = page == 0,
                    hasLoadMoreError = page > 0,
                )
            }
        }
    }

    companion object {
        private const val TAG = "PhotosViewModel"
    }
}
