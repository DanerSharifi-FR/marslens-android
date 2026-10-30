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

    init {
        loadPhotos()
    }

    fun loadPhotos() {
        // avoid duplicate requests from repeated taps (could be problematic as it was during a manual test)
        if (loadJob?.isActive == true) return

        _uiState.value = PhotosUiState()

        loadJob = viewModelScope.launch {
            try {
                val result = repository.getPhotos(
                    mission = mission,
                    sol = sol,
                    page = 0,
                )

                _uiState.value = PhotosUiState(
                    photos = result.photos,
                    isLoading = false,
                    total = result.total,
                    hasMore = result.hasMore,
                )
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                Log.e(TAG, "Photo request failed", exception)

                _uiState.value = PhotosUiState(
                    isLoading = false,
                    hasError = true,
                )
            }
        }
    }

    companion object {
        private const val TAG = "PhotosViewModel"
    }
}