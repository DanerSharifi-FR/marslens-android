package fr.danersharifi.marslens.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object MarsClient {

    private val json = Json {
        // ignore API fields we don't use (course best practice)
        ignoreUnknownKeys = true
    }

    fun create(): MarsApiService {
        return Retrofit.Builder()
            .baseUrl("https://mars.nasa.gov/api/")
            .addConverterFactory(
                json.asConverterFactory(
                    contentType = "application/json".toMediaType(),
                ),
            )
            .build()
            .create(MarsApiService::class.java)
    }
}