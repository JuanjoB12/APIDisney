package com.example.apidisney

import com.example.apidisney.json.DisneyResponse
import retrofit2.Response

interface APIService {
    suspend fun getDisney(imageUrl: String): Response<DisneyResponse>
}