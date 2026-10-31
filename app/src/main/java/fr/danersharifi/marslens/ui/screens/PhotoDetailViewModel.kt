package fr.danersharifi.marslens.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.danersharifi.marslens.data.repository.MarsRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PhotoDetailViewModel(
    private val photoId: Int,
    private val repository: MarsRepository = MarsRepository(),
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        value = PhotoDetailUiState(),
    )
    val uiState = _uiState.asStateFlow()

    private var loadJob: Job? = null

    init {
        loadPhoto()
    }

    fun loadPhoto() {
        // I tried "Try again" repeatedly, it can start overlapping requests
        // So we ignore retries while the previous request is still running
        if (loadJob?.isActive == true) return

        _uiState.value = PhotoDetailUiState()

        loadJob = viewModelScope.launch {
            try {
                val photo = repository.getPhoto(
                    photoId = photoId,
                )

                _uiState.value = PhotoDetailUiState(
                    photo = photo,
                    isLoading = false,
                )
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                Log.e(TAG, "Photo detail request failed", exception)

                _uiState.value = PhotoDetailUiState(
                    isLoading = false,
                    hasError = true,
                )
            }
        }
    }

    companion object {
        private const val TAG = "PhotoDetailViewModel"
    }
}